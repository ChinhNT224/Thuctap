
package com.bridgelabz.hospital.service;

import java.util.List;

import com.bridgelabz.hospital.dto.UserDto;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.request.LoginInformation;
import com.bridgelabz.hospital.request.PasswordUpdate;

public interface UserServices {

	Users login(LoginInformation information);
	boolean register(UserDto information);
	boolean verify(String token) throws Exception;
	boolean isUserExist(String email);
	boolean update(PasswordUpdate information, String token);
	List<Users> getUsers();
	Users getSingleUser(String token);
	Users findById(Long id);
	Users save(Users users);
	List<Users> getUsersDeleted();
    List<Users> searchData(String name);
}
