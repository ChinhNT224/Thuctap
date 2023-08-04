package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.dto.CustomerDto;
import com.bridgelabz.hospital.dto.OrderDetailDto;
import com.bridgelabz.hospital.dto.OrderInforDto;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.request.LoginInformation;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    boolean register(CustomerDto customerDto);

    boolean isValidCredentials(String email, String password);

    Customer findByEmail(String email);

    String generateToken(String email);

    boolean verifyCustomer(String token);

    Customer getCustomerById(long customerId);

    Customer login(CustomerDto information);

    void addOrder(Order order);

    Order getOrderById(int orderId);

    void deleteOrderIfPendingConfirmation(Order order);

    List<OrderInforDto> getOrdersByCustomerId(long customerId);

    List<String> searchPatients(Customer customer, String query);
}
