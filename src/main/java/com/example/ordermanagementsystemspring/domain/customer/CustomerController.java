package com.example.ordermanagementsystemspring.domain.customer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .body(customerService.createCustomer(customerDto));
    }
}
