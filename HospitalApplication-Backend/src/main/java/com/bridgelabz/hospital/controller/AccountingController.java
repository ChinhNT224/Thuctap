package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.dto.OrderAccDTO;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.repository.CustomerRepository;
import com.bridgelabz.hospital.repository.OrderRepository;
import com.bridgelabz.hospital.repository.UserRepository;
import com.bridgelabz.hospital.response.Response;
import com.bridgelabz.hospital.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AccountingController {

    @Autowired
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public AccountingController(OrderRepository orderRepository, UserRepository userRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping("accounting/confirmed-orders")
    public List<OrderAccDTO> getConfirmedOrders() {
        List<OrderAccDTO> orderAccDTOList = new ArrayList<>();

        List<Order> confirmedOrders = orderRepository.findByUserConfirmedBy_Role("reception");
        for (Order order : confirmedOrders) {
            OrderAccDTO orderAccDTO = new OrderAccDTO(
                    order.getOrder_id(),
                    order.getHo_ten_nguoi_benh(),
                    order.getUserCreatedBy().getName(),
                    order.getUserConfirmedBy() != null ? order.getUserConfirmedBy().getName() : null,
                    order.getNgay_tao(),
                    order.getNgay_tiep_nhan(),
                    order.getTrang_thai()
            );
            orderAccDTOList.add(orderAccDTO);
        }

        return orderAccDTOList;
    }

    @GetMapping("accounting/order-stats")
    public Map<String, Integer> getOrderStats(@RequestParam String timePeriod) {
        LocalDateTime startDate;
        LocalDateTime endDate = LocalDateTime.now();

        switch (timePeriod) {
            case "today":
                startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
                break;
            case "this-month":
                startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                break;
            case "this-year":
                startDate = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                break;
            default:
                throw new IllegalArgumentException("Invalid time period");
        }

        int confirmedOrders = orderRepository.countConfirmedByTimePeriod(startDate, endDate);

        int newCustomers = customerRepository.countByCreatedDate(startDate);

        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalOrders", confirmedOrders);
        stats.put("confirmedOrders", confirmedOrders);
        stats.put("newCustomers", newCustomers);

        return stats;
    }


}
