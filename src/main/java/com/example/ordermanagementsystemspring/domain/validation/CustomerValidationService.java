package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerValidationService {

    @Resource
    private CustomerRepository customerRepository;

    public void customersNotFound() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerException("Customers not found!");
        }
    }

    public void customerNotFound(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerException("Customer id " + id + " not found!");
        }
    }

    public void customerDataNotFound(CustomerRequest request) {
        if (request.getFullName() == null || request.getEmail() == null || request.getTelephone() == null) {
            throw new CustomerException("Customer data not found!");
        }
    }
}
