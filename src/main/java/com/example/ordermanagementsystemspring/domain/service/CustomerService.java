package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.exception.ExceptionCodes;
import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.CustomerMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerRepository customerRepository;

    public CustomerDto save(CustomerDto customerDto) {
        log.info("Request to createCustomer Customer : {}", customerDto);

        Customer customer = customerMapper.toEntity(customerDto);
        customer = customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();

        return customerMapper.toDtoList(customers);
    }

    public CustomerDto findById(Long id) {
        log.info("Request Customer by id : {}", id);

        Optional<Customer> customer =
                Optional.ofNullable(customerRepository
                        .findById(id)
                        .orElseThrow(() -> new CustomerException(
                                "Customer #" + id + " not found", ExceptionCodes.CUSTOMER_NOT_FOUND)));;

        return customerMapper.toDto(customer.get());
    }
}
