package com.example.ordermanagementsystemspring.domain.customer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerRepository customerRepository;

    public CustomerDto save(CustomerDto customerDto) {
        log.debug("Request to createCustomer Customer : {}", customerDto);

        Customer customer = customerMapper.toEntity(customerDto);
        customer = customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }
}
