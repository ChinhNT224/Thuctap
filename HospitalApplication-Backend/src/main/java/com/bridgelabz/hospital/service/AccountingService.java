package com.bridgelabz.hospital.service;

import com.bridgelabz.hospital.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountingService {

    @Autowired
    private OrderRepository orderRepository;

    public int getRegistrationsCountByDateMonthYear(String date, String month, String year) {
        // Implement the logic to query the database and get the registrations count
        // based on the provided date, month, and year parameters.
        // You can use the OrderRepository to query the database and count the registrations.

        // For example, if you want to count the registrations for a specific date:
        if (date != null) {
            LocalDate localDate = LocalDate.parse(date);
            return orderRepository.countOrdersByNgayTao(localDate);
        }

        // Similarly, you can implement logic to count registrations for a specific month and year.

        return 0;
    }
}

