package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.exception.OrderLineException;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderLineMapper;
import com.example.ordermanagementsystemspring.domain.validation.OrderLineValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderValidationService;
import com.example.ordermanagementsystemspring.domain.validation.ProductValidationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Resource
    private OrderLineValidationService orderLineValidationService;

    @Resource
    private ProductValidationService productValidationService;

    @Resource
    private OrderValidationService orderValidationService;

    public OrderLineDto save(OrderLineRequest request) {
        log.info("Request to save Order Line : {}", request);

        orderLineValidationService.OrderLineDataNotFound(request);
        productValidationService.productNotFound(request.getProductId());
        orderValidationService.orderNotFound(request.getOrderId());

        OrderLine orderLine = orderLineMapper.requestToEntity(request);

        Optional<Product> product = productRepository.findById(request.getProductId());
        orderLine.setProduct(product.orElse(null));

        Optional<Order> order = orderRepository.findById(request.getOrderId());
        orderLine.setOrder(order.orElse(null));

        orderLineRepository.save(orderLine);

        return orderLineMapper.toDto(orderLine);
    }

    public List<OrderLineDto> findByProduct(Long productId) {
        log.info("Request to find Order Line by Product id : {}", productId);

        productValidationService.productNotFound(productId);
        orderLineValidationService.orderLinesByProductNotFound(productId);

        List<OrderLine> orderLines = orderLineRepository.findAllByProductId(productId);

        return orderLineMapper.toDtoList(orderLines);
    }

    public OrderLineDto update(OrderLineDto orderLineDto) {
        log.info("Request to update Order Line : {}", orderLineDto);

        orderLineValidationService.orderLineNotFound(orderLineDto.getId());

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

        orderLineValidationService.orderLineNotFound(id);

        orderLineRepository.deleteById(id);
    }
}
