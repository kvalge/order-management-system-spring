package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.AppException;
import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerValidationServiceTest {

    @InjectMocks
    private CustomerValidationService validationService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void customerDataNotFound() {

        assertThatThrownBy(() ->
        {
            throw new CustomerException("Customer data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Customer data not found!");

        assertThrows(CustomerException.class, () -> {
            CustomerRequest request = new CustomerRequest();
            validationService.customerDataNotFound(request);
        });
    }

    @Test
    void customerDtoDataNotFound() {
        assertThatThrownBy(() ->
        {
            throw new CustomerException("Customer data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Customer data not found!");
    }

    @Test
    void customerAlreadyExists() {
        List<Customer> customers = new ArrayList<>();

        Customer customer = new Customer();
        customer.setFullName("Full Name");
        customer.setEmail("Email");
        customer.setTelephone("56112233");
        customers.add(customer);

        CustomerRequest request = new CustomerRequest();
        request.setFullName("Full Name");
        request.setEmail("Email");
        request.setTelephone("56112233");

        when(customerRepository.findAll()).thenReturn(customers);

        assertThatThrownBy(() ->
        {
            throw new CustomerException("Customer data already exists!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Customer data already exists!");

        assertThrows(CustomerException.class, () -> {
            validationService.customerAlreadyExists(request);
        });
    }

    @Test
    void customersNotFound() {
        assertThatThrownBy(() ->
        {
            throw new CustomerException("Customers not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Customers not found!");
    }

    @Test
    void customerNotFound() {
        long id = 1L;
        assertThatThrownBy(() ->
        {
            throw new CustomerException("Customer id " + id + " not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Customer id " + id + " not found!");
    }
}