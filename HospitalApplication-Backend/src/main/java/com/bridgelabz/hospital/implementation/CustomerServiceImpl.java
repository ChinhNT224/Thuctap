package com.bridgelabz.hospital.implementation;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.bridgelabz.hospital.dto.CustomerDto;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.exception.UserException;
import com.bridgelabz.hospital.repository.CustomerRepository;
import com.bridgelabz.hospital.request.LoginInformation;
import com.bridgelabz.hospital.service.CustomerService;
import com.bridgelabz.hospital.util.EmailProviderService;
import com.bridgelabz.hospital.util.JwtGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.hospital.repository.OrderRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder encryption;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private EmailProviderService emailProviderService;

    public CustomerServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public boolean register(CustomerDto information) {
        Customer customer = customerRepository.findByEmail(information.getEmail());
        if (customer == null) {
            Customer newCustomer = modelMapper.map(information, Customer.class);
            newCustomer.setCreatedDate(LocalDateTime.now());
            newCustomer.setActive(1);
            String encryptedPassword = encryption.encode(information.getPassword());
            newCustomer.setPassword(encryptedPassword);
            newCustomer.setVerified(false);
            newCustomer = customerRepository.save(newCustomer);

            String verificationLink = "http://localhost:8080/customer/verify/" + jwtGenerator.jwtToken(newCustomer.getCustomerId());
            emailProviderService.sendMail(newCustomer.getEmail(), "Verification", verificationLink);
            return true;
        } else {
            throw new UserException("Customer already exists with the same email address");
        }
    }

    @Override
    public boolean isValidCredentials(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return encryption.matches(password, customer.getPassword());
        } else {
            throw new UserException("Customer not found with the provided email");
        }
    }

    @Override
    public String generateToken(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return jwtGenerator.jwtToken(customer.getCustomerId());
        } else {
            throw new UserException("Customer not found with the provided email");
        }
    }

    @Override
    @Transactional
    public boolean verifyCustomer(String token) {
        long customerId = jwtGenerator.parseJWT(token);
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer != null) {
            customer.setVerified(true);
            customerRepository.save(customer);
            return true;
        } else {
            throw new UserException("Invalid verification token");
        }
    }


    @Override
    public Customer getCustomerById(long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer login(LoginInformation information) {
        return null;
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

}
