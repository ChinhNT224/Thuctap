package com.bridgelabz.hospital.repository;

import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);

	@Query("SELECT COUNT(c) FROM Customer c WHERE c.createdDate >= :startDate")
	int countByCreatedDate(LocalDateTime startDate);

	@Query(value = "select * from customer where customer_id=?", nativeQuery = true)
	Optional<Customer> findCustomerById(Long id);
}
