package com.bridgelabz.hospital.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.exception.AdminNotFoundException;
import com.bridgelabz.hospital.repository.CustomerRepository;
import com.bridgelabz.hospital.service.IAdminService;
import com.bridgelabz.hospital.util.JwtGenerator;

@Service
public class AdminServiceImpl implements IAdminService {

//	@Autowired
//	private IOrderStatusRepository orderStatusRepo;
//
	@Autowired
	CustomerRepository userRepo;

	@Autowired
	JwtGenerator jwt;

}
