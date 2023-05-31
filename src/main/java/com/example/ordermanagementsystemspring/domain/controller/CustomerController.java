package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.CustomerService;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
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
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerRequest request) {
        log.info("REST request to create Customer");

        return ResponseEntity
                .ok()
                .body(customerService.save(request));
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

    @PutMapping(value = "/customer", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto) {
        log.info("REST request to update Customer");

        if (customerDto == null) {
            throw new CustomerException("Customer data are missing");
        }

        return ResponseEntity
                .ok()
                .body(customerService.update(customerDto));
    }

    @PatchMapping(value = "/customer/{id}", produces = {"application/json"}, consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<CustomerDto> partialUpdateCustomer(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody CustomerDto customerDto) {
        log.info("REST request to partial update Customer");

        if (customerDto == null) {
            throw new CustomerException("Customer data are missing");
        }
        customerDto.setId(id);

        return ResponseEntity
                .ok()
                .body(customerService.partialUpdate(customerDto));
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.info("REST request to delete Customer : {}", id);

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
