package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.entity.Order;

public interface ReceptionService {
    Order getOrderById(int orderId);
    void updateOrder(Order order);

    // Các phương thức khác liên quan đến việc xác nhận đơn hàng
}
