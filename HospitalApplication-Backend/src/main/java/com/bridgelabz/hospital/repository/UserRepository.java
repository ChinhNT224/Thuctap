package com.bridgelabz.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.bridgelabz.hospital.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

    @Query(value = "select * from users where user_id=?", nativeQuery = true)
    Optional<Users> findUserById(Long id);
    @Query(value = "select * from users u where active=1 and (?1 is null or name like %?1%)", nativeQuery = true)
    Page<Users> GetAll(String name, Pageable pageable);
    @Query(value = "select * from users where ?1 is null or active=0 and name like %?1%", nativeQuery = true)
    Page<Users> GetAllDeleted(String name, Pageable pageable);
   @Query("select u from  Users u where u.email =:name and u.isdelete is null")
    List<Users> getdata(String name);
    @Query("select u from  Users u where u.isdelete is null ")
    List<Users>getAll();

}
