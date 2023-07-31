package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.dto.CustomerDto;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.response.Response;
import com.bridgelabz.hospital.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/registration")
    public ResponseEntity<Response> registration(@RequestBody CustomerDto information) {
        boolean result = customerService.register(information);
        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("Registration successful", 200));
        } else {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                    .body(new Response("Customer already exists", 400));
        }
    }

    @PostMapping("/customer/login")
    public ResponseEntity<Response> login(@RequestBody CustomerDto loginInformation) {
        boolean isValidCredentials = customerService.isValidCredentials(loginInformation.getEmail(), loginInformation.getPassword());
        if (isValidCredentials) {
            String token = customerService.generateToken(loginInformation.getEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header("Authorization", "Bearer " , "Login successful")
                    .body(new Response(token, 200));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response("Invalid credentials", 401));
        }
    }

    @GetMapping("/customer/verify/{token}")
    public ResponseEntity<Response> customerVerification(@PathVariable("token") String token) {
        boolean isVerified = customerService.verifyCustomer(token);
        if (isVerified) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new Response("Customer account verified", 200));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response("Invalid verification token", 400));
        }
    }

    // Thêm các phương thức khác cho Customer tương tự như UserController
    // Ví dụ:

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Response> getCustomer(@PathVariable long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("Customer information", 200, customer));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Customer not found", 404));
        }
    }

    @PostMapping("/customer/{customerId}/orders")
    public ResponseEntity<Response> addOrderToCustomer(@PathVariable long customerId, @RequestBody Order order) {
        try {
            // Fetch the customer from the database based on customerId
            Customer customer = customerService.getCustomerById(customerId);

            // Set the customer as the creator of the order
            order.setUserCreatedBy(customer);

            order.setTrang_thai("pending");

            // Save the order to the database
            customerService.addOrder(order);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("Order added successfully", 201));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Customer not found", 404));
        }
    }

}