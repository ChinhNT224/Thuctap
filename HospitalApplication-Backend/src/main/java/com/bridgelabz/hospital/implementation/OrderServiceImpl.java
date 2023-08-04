package com.bridgelabz.hospital.implementation;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.repository.OrderRepository;
import com.bridgelabz.hospital.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> FindById(int id) {
        return orderRepository.findById(id);
    }
}
