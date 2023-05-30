package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    Order order = new Order();

    @BeforeEach
    void setup() {
        Customer customer = new Customer();
        customer.setId(7L);
        customer.setRegistrationCode("Customer Registration Code");
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer Email");
        customer.setTelephone("55443322");

        order.setId(6L);
        order.setSubmissionDate(LocalDate.ofEpochDay(2023-05-15));
        order.setCustomer(customer);
    }

    @Test
    void findAllBySubmissionDate() {
        List<Order> orders = orderRepository.findAllBySubmissionDate(LocalDate.ofEpochDay(2023 - 05 - 15));

        assertNotNull(orders);
    }

    @Test
    void findAllByCustomerId() {
    }
}