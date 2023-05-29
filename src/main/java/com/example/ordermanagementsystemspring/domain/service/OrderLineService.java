package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.exception.OrderLineException;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderLineMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderLineService {

    @Resource
    private OrderLineMapper orderLineMapper;

    @Resource
    private OrderLineRepository orderLineRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private OrderRepository orderRepository;

    public OrderLineDto save(OrderLineDto orderLineDto) {
        log.info("Request to save Order Line : {}", orderLineDto);

        OrderLine orderLine = orderLineMapper.toEntity(orderLineDto);
        Optional<Product> product = productRepository.findById(orderLineDto.getProductId());
        orderLine.setProduct(product.get());
        Optional<Order> order = orderRepository.findById(orderLineDto.getOrderId());
        orderLine.setOrder(order.get());
        orderLineRepository.save(orderLine);

        OrderLineDto dto = orderLineMapper.toDto(orderLine);
        dto.setProductId(orderLineDto.getProductId());
        dto.setOrderId(orderLineDto.getOrderId());

        return dto;
    }

    public OrderLineDto update(OrderLineDto orderLineDto) {
        log.info("Request to update Order Line : {}", orderLineDto);

        OrderLine orderLine = orderLineRepository
                .findById(orderLineDto.getId())
                .orElseThrow(() -> new OrderLineException("Order Line #" + orderLineDto.getId() + " not found"));
        orderLineMapper.update(orderLine, orderLineDto);
        orderLineRepository.save(orderLine);

        OrderLineDto dto = orderLineMapper.toDto(orderLine);
        dto.setProductId(orderLine.getProduct().getId());
        dto.setOrderId(orderLine.getOrder().getId());

        return dto;
    }

    public void delete(Long id) {
        log.info("Request to delete Order Line by id : {}", id);

        orderLineRepository.deleteById(id);
    }
}
