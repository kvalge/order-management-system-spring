package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private CustomerRepository customerRepository;

    public OrderDto save(OrderDto orderDto) {
        log.info("Request to save Order : {}", orderDto);

        Order order = orderMapper.toEntity(orderDto);
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        order.setCustomer(customer.get());
        Date date = new Date();
        order.setSubmissionDate(date);
        orderRepository.save(order);

        OrderDto dto = orderMapper.toDto(order);
        dto.setCustomerId(customer.get().getId());

        return dto;
    }
}
