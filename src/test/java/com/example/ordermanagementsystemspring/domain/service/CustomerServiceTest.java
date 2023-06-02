package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.CustomerMapper;
import com.example.ordermanagementsystemspring.domain.validation.CustomerValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerValidationService validationService;

    Customer customer = new Customer();

    CustomerRequest request = new CustomerRequest();

    CustomerDto customerDto = new CustomerDto();
    List<Customer> customers = new ArrayList<>();
    List<CustomerDto> customerDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customer.setId(1L);
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer email");
        customer.setTelephone("Customer telephone");

        customers.add(customer);

        request.setFullName("Customer Full Name");
        request.setEmail("Customer email");
        request.setTelephone("Customer telephone");

        customerDto.setFullName("Customer Full Name");
        customerDto.setEmail("Customer email");
        customerDto.setTelephone("Customer telephone");

        customerDtos.add(customerDto);

        when(customerRepository.findAllById(Collections.singleton(anyLong()))).thenReturn(customers);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);
        when(customerMapper.toDto(customer)).thenReturn(customerDto);
    }

    @Test
    void save() {
        Mockito.doNothing().when(validationService).customerDataNotFound(request);
        Mockito.doNothing().when(validationService).customerAlreadyExists(request);
        when(customerMapper.requestToEntity(request)).thenReturn(customer);

        CustomerDto dto = customerService.save(request);

        assertNotNull(dto);
        assertEquals(customerDto.getFullName(), dto.getFullName());
    }

    @Test
    void findAll() {
        Mockito.doNothing().when(validationService).customersNotFound();
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toDtoList(customers)).thenReturn(customerDtos);

        List<CustomerDto> dtoList = customerService.findAll();

        assertThat(dtoList).isNotNull().isNotEmpty().hasSize(1);
        assertEquals(customer.getFullName(), dtoList.get(0).getFullName());
    }

    @Test
    void findById() {
        CustomerDto dto = customerService.findById(customer.getId());

        assertNotNull(dto);
        assertEquals(customerDto.getFullName(), dto.getFullName());
    }

    @Test
    void update() {
    }

    @Test
    void partialUpdate() {
    }

    @Test
    void delete() {
    }
}