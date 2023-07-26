package com.bridgelabz.hospital.controller;

import com.bridgelabz.hospital.response.Response;
import com.bridgelabz.hospital.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    @GetMapping("/accounting/registrations")
    public ResponseEntity<Response> getRegistrationsByDate(@RequestParam(name = "date", required = false) String date,
                                                           @RequestParam(name = "month", required = false) String month,
                                                           @RequestParam(name = "year", required = false) String year) {
        // Validate input parameters (date, month, year)

        // Call the accountingService to get the registrations count
        int registrationsCount = accountingService.getRegistrationsCountByDateMonthYear(date, month, year);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response("Registrations count", 200, registrationsCount));
    }
}
