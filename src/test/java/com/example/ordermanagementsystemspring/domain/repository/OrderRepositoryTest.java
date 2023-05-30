package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    Order order = new Order();
    Customer customer = new Customer();
    List<Order> orders = new ArrayList<>();

    @BeforeEach
    void setup() {
        customer.setRegistrationCode("Customer Registration Code");
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer Email");
        customer.setTelephone("55443322");

        order.setSubmissionDate(LocalDate.ofEpochDay(2023-05-15));
        order.setCustomer(customer);

        orders.add(order);

        testEntityManager.persist(customer);
        testEntityManager.persist(order);
    }

    @Test
    void findAllBySubmissionDate() {
        List<Order> orderList = orderRepository.findAllBySubmissionDate(order.getSubmissionDate());

        assertEquals(1, orderList.size());
        assertEquals("Customer Full Name", orderList.get(0).getCustomer().getFullName());
    }

    @Test
    void findAllByCustomerId() {
    }
}