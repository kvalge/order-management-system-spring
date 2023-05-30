package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.OrderException;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderValidationService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderLineRepository orderLineRepository;

    public void ordersNotFound() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new OrderException("Orders not found!");
        }
    }

    public void orderNotFound(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new OrderException("Order id " + id + " not found!");
        }
    }

    public void ordersByDateNotFound(LocalDate date) {
        List<Order> orders = orderRepository.findAllBySubmissionDate(date);
        if (orders.isEmpty()) {
            throw new OrderException("Orders searched by date " + date + " not found!");
        }
    }

    public void ordersByCustomerNotFound(Long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        if (orders.isEmpty()) {
            throw new OrderException("Orders searched by customer id " + id + " not found!");
        }
    }
}
