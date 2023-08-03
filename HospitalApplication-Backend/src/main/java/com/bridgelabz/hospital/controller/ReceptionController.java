package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.entity.Order;
import com.bridgelabz.hospital.entity.Users;
import com.bridgelabz.hospital.response.Response;
import com.bridgelabz.hospital.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@CrossOrigin
public class ReceptionController {
    @Autowired
    private ReceptionService receptionService;

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
}

