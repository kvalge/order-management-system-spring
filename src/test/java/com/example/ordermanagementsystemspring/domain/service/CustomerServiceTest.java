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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @BeforeEach
    void setUp() {
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer email");
        customer.setTelephone("Customer telephone");

        request.setFullName("Customer Full Name");
        request.setEmail("Customer email");
        request.setTelephone("Customer telephone");

        customerDto.setFullName("Customer Full Name");
        customerDto.setEmail("Customer email");
        customerDto.setTelephone("Customer telephone");

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
    }

    @Test
    void findById() {
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