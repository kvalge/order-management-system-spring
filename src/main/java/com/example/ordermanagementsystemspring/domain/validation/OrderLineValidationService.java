package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.OrderLineException;
import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineValidationService {

    @Resource
    private OrderLineRepository orderLineRepository;

    public void OrderLineDataNotFound(OrderLineRequest request) {
        if (request.getQuantity() == 0 || request.getProductId() == 0 || request.getOrderId() == 0) {
            throw new OrderLineException("Order Line data not found!");
        }
    }

    public void orderLineDtoDataNotFound(OrderLineDto orderLineDto) {
        if (orderLineDto.getId() == null ||
                orderLineDto.getQuantity() == null ||
                orderLineDto.getProductId() == null ||
                orderLineDto.getOrderId() == null) {
            throw new OrderLineException("Order Line data not found!");
        }
    }

    public void orderLinesByProductNotFound(Long id) {
        List<OrderLine> orderLines = orderLineRepository.findAllByProductId(id);
        if (orderLines.isEmpty()) {
            throw new OrderLineException("Order Lines with product id " + id + " not found!");
        }
    }

    public void orderLineNotFound(Long id) {
        Optional<OrderLine> orderLine = orderLineRepository.findById(id);
        if (orderLine.isEmpty()) {
            throw new OrderLineException("Order Line id " + id + " not found!");
        }
    }
}
