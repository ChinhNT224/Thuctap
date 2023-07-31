package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.dto.CustomerDto;
import com.bridgelabz.hospital.dto.OrderDto;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.request.LoginInformation;

public interface CustomerService {

    boolean register(CustomerDto customerDto);

    boolean isValidCredentials(String email, String password);

    String generateToken(String email);

    boolean verifyCustomer(String token);

    Customer getCustomerById(long customerId);

    Customer login(LoginInformation information);

    void addOrder(Order order);

}