package com.bridgelabz.hospital.implementation;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.repository.OrderRepository;
import com.bridgelabz.hospital.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceptionServiceImpl implements ReceptionService {


    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    // Triển khai các phương thức khác liên quan đến việc xác nhận đơn hàng
}

