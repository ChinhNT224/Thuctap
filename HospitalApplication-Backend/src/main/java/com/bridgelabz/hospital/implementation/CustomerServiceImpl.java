package com.bridgelabz.hospital.implementation;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bridgelabz.hospital.dto.CustomerDto;
import com.bridgelabz.hospital.dto.OrderInforDto;
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
            newCustomer.setCreatedDate(new Date(System.currentTimeMillis()));
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
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
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
    public Customer login(CustomerDto information) {
        return null;
    }

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<OrderInforDto> getOrdersByCustomerId(long customerId ,String trangThai) {
         List<Order> orders;
        if (trangThai.equals("ALL") ){
            orders = orderRepository.findAllBycustomerId(customerId);
        }else {
           orders = orderRepository.findByUserCreatedByCustomerId(customerId, trangThai);
        }
        List<OrderInforDto> orderInfos = new ArrayList<>();

        for (Order order : orders) {
            OrderInforDto orderInfo = new OrderInforDto();
            orderInfo.setHo_ten_nguoi_benh(order.getHo_ten_nguoi_benh());
            orderInfo.setNgay_tao(order.getNgay_tao());
            orderInfo.setNgay_tiep_nhan(order.getNgay_tiep_nhan());
            orderInfo.setTrang_thai(order.getTrang_thai());
            orderInfo.setOrder_id(order.getOrder_id());
            orderInfos.add(orderInfo);
        }

        return orderInfos;
    }

    @Override
    public void deleteOrderIfPendingConfirmation(Order order) {
        if ("CHO_XAC_NHAN".equals(order.getTrang_thai())) {
            order.setUserCreatedBy(null);
            orderRepository.delete(order);
        }
    }

    @Override
    public List<String> searchPatients(Customer customer, String query) {
        List<String> matchingPatientNames = new ArrayList<>();

        if (customer == null) {
            return matchingPatientNames;
        }
        List<Order> customerOrders = customer.getOrders();

        if (customerOrders == null) {
            return matchingPatientNames;
        }
        return matchingPatientNames;
    }
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

}




