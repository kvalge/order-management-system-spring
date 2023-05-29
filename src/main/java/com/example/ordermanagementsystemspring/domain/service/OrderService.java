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

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
        order.setSubmissionDate(LocalDate.now());

        orderRepository.save(order);

        OrderDto dto = orderMapper.toDto(order);
        dto.setCustomerId(customer.get().getId());

        return dto;
    }

    public List<OrderDto> findByDate(LocalDate date) {
        List<Order> orders = orderRepository.findAllBySubmissionDate(date);
        List<OrderDto> orderDtos = orderMapper.toDtoList(orders);
        for (Order order : orders) {
            for (OrderDto orderDto : orderDtos) {
                if (Objects.equals(order.getId(), orderDto.getId())) {
                    orderDto.setCustomerId(order.getCustomer().getId());
                }
            }
        }
        return orderDtos;
    }
}
