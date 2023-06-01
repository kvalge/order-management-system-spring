package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderMapper;
import com.example.ordermanagementsystemspring.domain.validation.CustomerValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderLineValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderValidationService;
import com.example.ordermanagementsystemspring.domain.validation.ProductValidationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private OrderValidationService orderValidationService;

    @Resource
    private ProductValidationService productValidationService;

    @Resource
    private OrderLineValidationService orderLineValidationService;

    @Resource
    private CustomerValidationService customerValidationService;

    public OrderDto save(OrderRequest request) {
        log.info("Request to save Order : {}", request);

        Order order = orderMapper.requestToOrder(request);

        Optional<Customer> customer = customerRepository.findById(request.getCustomerId());
        order.setCustomer(customer.orElse(null));
        order.setSubmissionDate(LocalDate.now());
        orderRepository.save(order);

        return orderMapper.toDto(order);
    }

    public List<OrderDto> findAll() {
        orderValidationService.ordersNotFound();

        List<Order> orders = orderRepository.findAll();

        return orderMapper.toDtoList(orders);
    }

    public OrderDto findById(Long id) {
        log.info("Request to find Order by id : {}", id);

        orderValidationService.orderNotFound(id);

        Optional<Order> order = orderRepository.findById(id);

        return orderMapper.toDto(order.orElse(null));
    }

    public List<OrderDto> findByDate(LocalDate date) {
        log.info("Request to find Order by date : {}", date);

        orderValidationService.ordersByDateNotFound(date);

        List<Order> orders = orderRepository.findAllBySubmissionDate(date);

        return orderMapper.toDtoList(orders);
    }

    public List<OrderDto> findByProduct(Long productId) {
        log.info("Request to find Order by Product id : {}", productId);

        productValidationService.productNotFound(productId);
        orderLineValidationService.orderLinesByProductNotFound(productId);

        List<OrderLineDto> orderLineDtos = orderLineService.findByProduct(productId);

        List<OrderDto> orderDtos = new ArrayList<>();

        for (OrderLineDto orderLineDto : orderLineDtos) {
            Long orderId = orderLineDto.getOrderId();
            Optional<Order> order = orderRepository.findById(orderId);
            OrderDto dto = orderMapper.toDto(order.orElse(null));
            orderDtos.add(dto);
        }

        return orderDtos;
    }

    public List<OrderDto> findByCustomer(Long customerId) {
        log.info("Request to find Order by Customer id : {}", customerId);

        customerValidationService.customerNotFound(customerId);
        orderValidationService.ordersByCustomerNotFound(customerId);

        List<Order> orders = orderRepository.findAllByCustomerId(customerId);

        return orderMapper.toDtoList(orders);
    }

    /**
     * Deletes simultaneously also all Order Lines of the Order.
     */
    public void delete(Long id) {
        log.info("Request to delete Order by id : {}", id);

        orderValidationService.orderNotFound(id);

        orderRepository.deleteById(id);
    }
}
