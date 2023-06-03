package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.AppException;
import com.example.ordermanagementsystemspring.domain.exception.OrderException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OrderValidationServiceTest {

    @Test
    void ordersNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderException("Orders not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Orders not found!");
    }

    @Test
    void orderNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderException("Order id not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Order id not found!");
    }

    @Test
    void ordersByDateNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderException("Orders searched by date not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Orders searched by date not found!");
    }

    @Test
    void ordersByCustomerNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderException("Orders searched by customer not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Orders searched by customer not found!");
    }
}