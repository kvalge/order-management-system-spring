package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Resource
    private OrderLineService orderLineService;

    @Resource
    private OrderLineRepository orderLineRepository;

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
        log.info("Request to find Order by date : {}", date);

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

    public List<OrderDto> findByProduct(Long productId) {
        log.info("Request to find Order by Product id : {}", productId);

        List<OrderLineDto> orderLineDtos = orderLineService.findByProduct(productId);

        List<OrderDto> orderDtos = new ArrayList<>();

        for (OrderLineDto orderLineDto : orderLineDtos) {
            Long orderId = orderLineDto.getOrderId();
            Optional<Order> order = orderRepository.findById(orderId);
            OrderDto orderDto = orderMapper.toDto(order.get());
            orderDto.setCustomerId(order.get().getCustomer().getId());
            orderDto.setSubmissionDate(order.get().getSubmissionDate());
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    public List<OrderDto> findByCustomer(Long customerId) {
        log.info("Request to find Order by Customer id : {}", customerId);

        List<Order> orders = orderRepository.findAllByCustomerId(customerId);

        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderDto dto = orderMapper.toDto(order);
            dto.setCustomerId(order.getCustomer().getId());
            orderDtos.add(dto);
        }

        return orderDtos;
    }

    /**
     * Deletes all Order Lines of the Order before deleting the Order.
     */
    public void delete(Long id) {
        log.info("Request to delete Order by id : {}", id);

        orderRepository.deleteById(id);
    }
}
