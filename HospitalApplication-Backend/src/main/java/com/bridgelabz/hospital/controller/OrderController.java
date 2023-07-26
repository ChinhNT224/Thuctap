package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.repository.OrderRepository;
import com.bridgelabz.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Thêm mới một bản ghi Order từ việc nhập của user có role là user
    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {

        // Xử lý các logic liên quan trước khi thêm Order
        System.out.println(order.getUserCreatedBy().getUserId());
        Users user = userRepository.findUserById(order.getUserCreatedBy().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        order.setUserCreatedBy(user);
        Order newOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // Lấy danh sách tất cả các bản ghi Order
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    // Lấy thông tin chi tiết của một bản ghi Order dựa trên order_id
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Cập nhật thông tin một bản ghi Order dựa trên order_id
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int orderId, @RequestBody Order updatedOrder) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            // Cập nhật thông tin của order từ updatedOrder
            order.setTrang_thai(updatedOrder.getTrang_thai());

            // Lấy thông tin userConfirmedBy dựa vào user_id_confirmed_by
            Long userConfirmedById = updatedOrder.getUserConfirmedBy().getUserId();
            Users userConfirmedBy = userRepository.findUserById(userConfirmedById)
                    .orElseThrow(() -> new EntityNotFoundException("UserConfirmedBy not found!"));
            order.setUserConfirmedBy(userConfirmedBy);

            Order updated = orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // Xóa một bản ghi Order dựa trên order_id
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            orderRepository.delete(order);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }
}
