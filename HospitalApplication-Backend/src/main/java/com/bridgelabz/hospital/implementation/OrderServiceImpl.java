package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        // Perform any necessary validations or business logic before saving
        // For example, you can set the userCreatedBy to the currently authenticated user
        // and set other default values if needed.

        // Save the order
        return orderRepository.save(order);
    }
}
