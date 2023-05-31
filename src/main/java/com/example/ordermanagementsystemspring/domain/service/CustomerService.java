package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.exception.ExceptionCodes;
import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.CustomerMapper;
import com.example.ordermanagementsystemspring.domain.validation.CustomerValidationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private CustomerValidationService validationService;

    public CustomerDto save(CustomerRequest request) {
        log.info("Request to save Customer : {}", request);

        Customer customer = customerMapper.requestToEntity(request);
        customer.setRegistrationCode(UUID.randomUUID().toString());
        customer = customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> findAll() {
        validationService.customersNotFound();

        List<Customer> customers = customerRepository.findAll();

        return customerMapper.toDtoList(customers);
    }

    public CustomerDto findById(Long id) {
        log.info("Request to find Customer by id : {}", id);

        validationService.customerNotFound(id);

        Optional<Customer> customer =
                Optional.ofNullable(customerRepository
                        .findById(id)
                        .orElseThrow(() -> new CustomerException(
                                "Customer #" + id + " not found", ExceptionCodes.CUSTOMER_NOT_FOUND)));

        return customerMapper.toDto(customer.get());
    }

    public CustomerDto update(CustomerDto customerDto) {
        log.info("Request to update Customer : {}", customerDto);

        validationService.customerNotFound(customerDto.getId());

        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new CustomerException("Customer #" + customerDto.getId() + " not found"));
        customerMapper.update(customer, customerDto);
        customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }


    public CustomerDto partialUpdate(CustomerDto customerDto) {
        log.info("Request to partially update Customer : {}", customerDto);

        validationService.customerNotFound(customerDto.getId());

        Customer customer = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new CustomerException("Customer #" + customerDto.getId() + " not found"));
        customerMapper.partialUpdate(customer, customerDto);
        customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    public void delete(Long id) {
        log.info("Request to delete Customer by id : {}", id);

        validationService.customerNotFound(id);

        customerRepository.deleteById(id);
    }
}
