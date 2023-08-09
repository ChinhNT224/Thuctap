package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);
    Optional<Order> FindById(int id);
    List<Order> findAllOrders();
}

