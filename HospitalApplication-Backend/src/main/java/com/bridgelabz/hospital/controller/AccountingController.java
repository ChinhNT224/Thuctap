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
import java.util.*;

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

        @GetMapping("/accounting/order-stats")
        public Map<String, Integer> getOrderStats(@RequestParam String timePeriod) {
            Date startDate;
            Date endDate;

            System.out.println("Received request with time period: " + timePeriod);

            switch (timePeriod) {
                case "today":
                    startDate = new Date(removeTimePart(new Date(System.currentTimeMillis())).getTime());
                    endDate = new Date(System.currentTimeMillis());
                    break;
                case "this-month":
                    startDate = new Date(removeTimePart(getFirstDayOfMonth()).getTime());
                    endDate = new Date(System.currentTimeMillis());
                    break;
                case "this-year":
                    startDate = new Date(removeTimePart(getFirstDayOfYear()).getTime());
                    endDate = new Date(System.currentTimeMillis());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time period");
            }

            System.out.println("Calculated start date: " + startDate);
            System.out.println("Calculated end date: " + endDate);

            int confirmedOrders = orderRepository.countConfirmedByTimePeriod(startDate, endDate);
            int totalOrders = orderRepository.countByTimePeriod(startDate, endDate);
            int newCustomers = customerRepository.countByCreatedDate(startDate);

            Map<String, Integer> stats = new HashMap<>();
            stats.put("totalOrders", totalOrders);
            stats.put("confirmedOrders", confirmedOrders);
            stats.put("newCustomers", newCustomers);

            System.out.println("Total Orders: " + totalOrders);
            System.out.println("Confirmed Orders: " + confirmedOrders);
            System.out.println("New Customers: " + newCustomers);

            return stats;
        }

    private Date removeTimePart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis()); // Chuyển đổi lại thành kiểu java.sql.Date
    }

    // Lấy ngày đầu tiên của tháng
    private Date getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis()); // Chuyển đổi lại thành kiểu java.sql.Date
    }

    // Lấy ngày đầu tiên của năm
    private Date getFirstDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis()); // Chuyển đổi lại thành kiểu java.sql.Date
    }
}
