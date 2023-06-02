package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.exception.OrderLineException;
import com.example.ordermanagementsystemspring.domain.service.OrderLineService;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderLineController {

    @Resource
    private OrderLineService orderLineService;

    @PostMapping(value = "/ordeline", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<OrderLineDto> createOrderLine(@RequestBody OrderLineRequest request) {
        log.info("REST request to create Order Line");

        return ResponseEntity
                .ok()
                .body(orderLineService.save(request));
    }

    @GetMapping(value = "/orderline/product", produces = {"application/json"})
    public ResponseEntity<List<OrderLineDto>> getOrderLinesByProduct(@RequestParam(required = false) Long productId) {
        log.info("REST request to get Order Lines by Product id");

        return ResponseEntity
                .ok()
                .body(orderLineService.findByProduct(productId));
    }

    @PutMapping(value = "/orderline", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<OrderLineDto> updateOrderLine(@RequestBody OrderLineDto orderLineDto) {
        log.info("REST request to update Order Line");

        if (orderLineDto == null) {
            throw new OrderLineException("Order Line data are missing");
        }

        return ResponseEntity
                .ok()
                .body(orderLineService.update(orderLineDto));
    }

    @DeleteMapping("/orderline/{id}")
    public void deleteOrderLine(@PathVariable Long id) {
        log.info("REST request to delete Order Line : {}", id);

        orderLineService.delete(id);
    }
}
