/**
 * 
 */
package com.bridgelabz.hospital.repository;

import java.util.List;

import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.request.PasswordUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author HP
 *
 */
public interface IUserRepository {
	Users save(Users users);

	Users getUser(String email);

	boolean verify(Long id);

	boolean upDate(PasswordUpdate information, Long token);

	Users getUserById(Long id );

	List<Users> getUsers();
	List<Users> GetAllDeleted();

}
