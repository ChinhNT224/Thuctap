/**
 * 
 */
package com.bridgelabz.hospital.implementation;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import com.bridgelabz.hospital.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.hospital.dto.UserDto;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.exception.UserException;
import com.bridgelabz.hospital.repository.IUserRepository;
import com.bridgelabz.hospital.request.LoginInformation;
import com.bridgelabz.hospital.request.PasswordUpdate;
import com.bridgelabz.hospital.response.EmailData;
import com.bridgelabz.hospital.response.MailResponse;
import com.bridgelabz.hospital.service.UserServices;
import com.bridgelabz.hospital.util.EmailProviderService;
import com.bridgelabz.hospital.util.JwtGenerator;
import com.bridgelabz.hospital.util.MailServiceProvider;

//import com.bridgelabz.hospital.util.RabbitMQSender;




@Service
public class UserServiceImplementation implements UserServices {
	private Users users = new Users();
	@Autowired
	private IUserRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encryption;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtGenerator generate;

	@Autowired
	private MailResponse response;
	
	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	
	@Override
	@Transactional
	public boolean register(UserDto information) {
		Users user = repository.getUser(information.getEmail());
		if (user == null) {
			users = modelMapper.map(information, Users.class);
			users.setCreatedDate(LocalDateTime.now());
			users.setActive(1);
			String epassword = encryption.encode(information.getPassword());
			// setting the some extra information and encrypting the password
			users.setPassword(epassword);
			System.out.println("password is" + epassword);
			users.setVerified(false);
			// calling the save method
			users = repository.save(users);
			String mailResponse = 
					"http://localhost:8080/user/verify/"+
					generate.jwtToken(users.getUserId());
			// setting the data to mail

//			mailObject.setEmail(information.getEmail());
//			mailObject.setMessage(mailResponse);
//			mailObject.setSubject("Verification");
//			rabbitMQSender.send(mailObject);
					emailData.setEmail(users.getEmail());
					emailData.setSubject("your Registration is successful");
					emailData.setBody(mailResponse);
					em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());
			System.out.println(mailResponse);
			return true;
		} else {
			throw new UserException("user already exist with the same mail id");
		}
	}

	@Override
	public Users login(LoginInformation information) {
		Users user = repository.getUser(information.getEmail());
		if (user != null) {
			String userRole = information.getRole();
			String fetchRole = user.getRole();
			if (userRole.equals(fetchRole)) { // Kiểm tra vai trò người dùng và vai trò yêu cầu phải khớp nhau
				Users userInfo = verifyPassword(user, information);
				return userInfo;
			} else {
				throw new UserException("Your are not Authorized person");
			}
		} else {
			throw new UserException("User Not present enter valid your email id");
		}
	}


	/**
	 * This is validate the token based on there role and 
	 * @param role
	 * @param token
	 * @return
	 */
	public boolean isValidToken(String role, String token) {
		long id;
		try {
			id = (long) generate.parseJWT(token);
			Users information = repository.getUserById(id);
			String userRole = information.getRole();
			System.out.println("actual Role is " + userRole);
			System.out.println("expected role is " + role);

			if (role.equals("admin")) {
				return userRole.equals("admin");
			} else if (role.equals("reception")) {
				return userRole.equals("reception");
			} else if (role.equals("user")) {
				return userRole.equals("user");
			} else if (role.equals("accounting")) {
				return userRole.equals("accounting");
			} else {
				throw new UserException("Invalid role specified");
			}
		} catch (Exception e) {
			throw new UserException("Invalid token or user not found");
		}
	}

	public Users verifyPassword(Users user, LoginInformation information) {
		if ((user.isVerified() == true)) {
			if (encryption.matches(information.getPassword(), user.getPassword())) {
				System.out.println(generate.jwtToken(user.getUserId()));
				return user;
			} else {
				throw new UserException("Invalid password");
			}
		} else {
			String mailResponse = response.formMessage("http://localhost:8080/user/verify",
					generate.jwtToken(user.getUserId()));
			MailServiceProvider.sendEmail(information.getEmail(), "verification", mailResponse);
			return user;
		}
	}

	/**
	 * Verifying the user based on there token
	 * 
	 * @param id
	 * @return generated token
	 */

	@Transactional
	@Override
	public boolean verify(String token) throws Exception {
		Long id = (long) generate.parseJWT(token);
		System.out.println("User id: " + id);
		repository.verify(id);
		return true;
	}

	/**
	 * checking the user is present or or not if present then it's will send a email
	 * to verify
	 *
	 * @param email
	 * @return boolean value
	 */
	@Override
	public boolean isUserExist(String email) {
		try {
			Users user = repository.getUser(email);
			if (user.isVerified() == true) {
				String mailResponse = response.formMessage("http://localhost:4200/update-password",
						generate.jwtToken(user.getUserId()));
				System.out.println(mailResponse);
				MailServiceProvider.sendEmail(user.getEmail(), "Reset Your Password", mailResponse);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new UserException("User doesn't exist");
		}
	}

	@Transactional
	@Override
	public boolean update(PasswordUpdate information, String token) {
		System.out.println("User information" + information.toString());
		if (information.getNewPassword().equals(information.getConfirmPassword())) {
			Long id = null;
			try {
				id = (long) generate.parseJWT(token);
				System.out.println("User id " + id);
				Users UpdateUser = repository.getUser(information.getEmail());
				System.out.println("updated user info" + UpdateUser);
				if (id == UpdateUser.getUserId()) {
					String epassword = encryption.encode(information.getConfirmPassword());
					information.setConfirmPassword(epassword);

					return repository.upDate(information, id);
				} else {
					throw new UserException("Please Enter valid Email ");
				}
			} catch (Exception e) {
				throw new UserException("invalid credentials");
			}
		} else {
			System.out.println("Password Not match");
			throw new UserException("invalid password");
		}
	}

	@Override
	public List<Users> getUsers() {
		// TODO Auto-generated method stub
		return repository.getUsers();
	}

	/**
	 * by this we can get the single user
	 *
	 * @param it's taking the token
	 * @return returning the single user
	 */
	@Transactional
	@Override
	public Users getSingleUser(String token) {
		Long id;
		try {
			id = (long) generate.parseJWT(token);
		} catch (Exception e) {
			throw new UserException("User doesn't exist");
		}
		
		if(isValidToken("admin", token)) {
		Users user = repository.getUserById(id);
		return user;
		}else {
			throw new UserException("token is not valid");
		}
	}

	@Override
	public Users findById(Long id) {
		return repository.getUserById(id);
	}

	@Override
	public Users save(Users users) {
		return repository.save(users);
	}

	@Override
	public List<Users> getUsersDeleted() {
		return repository.GetAllDeleted();
	}

	@Override
	public List<Users> searchData(String name) {
		if(name==""){
			return userRepository.getAll();
		}
		return  userRepository.getdata(name);
	}

}
