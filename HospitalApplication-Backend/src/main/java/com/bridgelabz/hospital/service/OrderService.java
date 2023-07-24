package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createNewOrder(Order order) {
        // Sử dụng phương thức save() để thêm mới bản ghi
        // Đối tượng order được truyền vào sẽ được thêm vào cơ sở dữ liệu
        return orderRepository.save(order);
    }

    public abstract Order createOrder(Order order);
}


