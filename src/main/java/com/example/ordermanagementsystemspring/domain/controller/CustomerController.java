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
        log.info("REST request to createCustomer Customer");
        return ResponseEntity
                .ok()
                .body(customerService.save(customerDto));
    }

    @GetMapping(value = "/customer", produces = {"application/json"})
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        log.info("REST request to get all Customers");
        return ResponseEntity
                .ok()
                .body(customerService.findAll());
    }

    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = "id", required = true) final Long id) {
        log.info("REST request to get Customer : {}", id);
        return ResponseEntity
                .ok()
                .body(customerService.findById(id));
    }
}
