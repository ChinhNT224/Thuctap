package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.dto.OrderInforDto;
import com.bridgelabz.hospital.entity.Customer;
import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.response.Response;
import com.bridgelabz.hospital.service.CustomerService;
import com.bridgelabz.hospital.service.OrderService;
import com.bridgelabz.hospital.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
public class ReceptionController {
    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/reception/orders/{orderId}/confirm")
    public ResponseEntity<Response> confirmOrder(@PathVariable int orderId, @RequestBody Users receptionUser) {
        try {
            // Fetch the order from the database based on orderId
            Order order = receptionService.getOrderById(orderId);

            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("Order not found", 404));
            }

            // Check if the receptionUser has the role "reception"
            if (!receptionUser.getRole().equalsIgnoreCase("reception")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Response("Unauthorized access", 401));
            }

            // Set the trạng thái of the order to "confirmed"
            order.setTrang_thai("Đã xác nhận");

            // Set the receptionUser as the user who confirmed the order
            order.setUserConfirmedBy(receptionUser);

            // Save the updated order to the database
            receptionService.updateOrder(order);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("Order confirmed successfully", 200));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Order not found", 404));
        }
    }

    @PostMapping("/reception/orders/{orderId}/refused")
    public ResponseEntity<Response> refusedOrder(@PathVariable int orderId, @RequestBody Users receptionUser) {
        try {
            // Fetch the order from the database based on orderId
            Order order = receptionService.getOrderById(orderId);

            if (order == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("Order not found", 404));
            }

            if (!receptionUser.getRole().equalsIgnoreCase("reception")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new Response("Unauthorized access", 401));
            }

            order.setTrang_thai("Từ chối");

            order.setUserConfirmedBy(receptionUser);

            receptionService.updateOrder(order);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("Order refused successfully", 200));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Order not found", 404));
        }
    }

    @GetMapping("/reception/AllOrder")
    public ResponseEntity<Response> findAllOrders() {
        List<Order> orders = orderService.findAllOrders();

        if (!orders.isEmpty()) {
            // Sắp xếp danh sách đơn hàng theo thứ tự orderID
            orders.sort(Comparator.comparingInt(Order::getOrder_id));

            List<OrderInforDto> orderDtos = new ArrayList<>();
            for (Order order : orders) {
                OrderInforDto orderDto = new OrderInforDto(
                        order.getHo_ten_nguoi_benh(),
                        order.getNgay_tao(),
                        order.getNgay_tiep_nhan(),
                        order.getTrang_thai(),
                        order.getOrder_id(),
                        order.getUserCreatedBy().getCustomerId() // Lấy thông tin customerId
                );
                orderDtos.add(orderDto);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("Danh sách đơn hàng", 200, orderDtos));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Không có đơn hàng nào tồn tại", 404, null));
        }
    }

    @GetMapping("/reception/customer/{customerId}/orders/{orderId}")
    public ResponseEntity<Response> getOrderDetails(@PathVariable long customerId, @PathVariable int orderId) {
        try {
            // Lấy thông tin khách hàng từ cơ sở dữ liệu dựa vào customerId
            Customer customer = customerService.getCustomerById(customerId);
            // Kiểm tra xem đơn hàng có tồn tại với orderId được cung cấp và thuộc về khách hàng
            Order order = customerService.getOrderById(orderId);

            // Lấy customerId từ userCreatedBy để so sánh với customerId được cung cấp
            long createdByCustomerId = order.getUserCreatedBy().getCustomerId();

            if (customerId == createdByCustomerId) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new Response("Chi tiết đơn hàng", 200, order));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("Đơn hàng không tồn tại", 404));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Khách hàng không tồn tại", 404));
        }
    }

}

