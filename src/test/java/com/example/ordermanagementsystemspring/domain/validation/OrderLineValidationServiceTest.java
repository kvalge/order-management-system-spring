package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.AppException;
import com.example.ordermanagementsystemspring.domain.exception.OrderLineException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class OrderLineValidationServiceTest {

    @Test
    void orderLineDataNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderLineException("Order Line data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Order Line data not found!");

    }

    @Test
    void orderLineDtoDataNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderLineException("Order Line data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Order Line data not found!");
    }

    @Test
    void orderLinesByProductNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderLineException("Order Lines product not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Order Lines product not found!");
    }

    @Test
    void orderLineNotFound() {
        assertThatThrownBy(() ->
        {
            throw new OrderLineException("Order Line id not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Order Line id not found!");
    }
}