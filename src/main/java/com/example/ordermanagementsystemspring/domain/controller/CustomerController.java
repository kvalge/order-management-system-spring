package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.CustomerService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping(value = "/customer", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        log.debug("REST request to createCustomer Customer");
        return ResponseEntity
                .ok()
                .body(customerService.save(customerDto));
    }

    @GetMapping(value = "/customer", produces = {"application/json"})
    public ResponseEntity<List<CustomerDto>> getAllUsers() {
        log.debug("REST request to get all Customers");
        return ResponseEntity
                .ok()
                .body(customerService.findAll());
    }
}
