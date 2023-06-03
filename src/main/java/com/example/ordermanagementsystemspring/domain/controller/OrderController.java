package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.OrderService;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping(value = "/order", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request) {
        log.info("REST request to create Order");

        return ResponseEntity
                .ok()
                .body(orderService.save(request));
    }

    @GetMapping(value = "/order", produces = {"application/json"})
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("REST request to get all Orders");

        return ResponseEntity
                .ok()
                .body(orderService.findAll());
    }

    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(value = "id", required = true) final Long id) {
        log.info("REST request to get Order : {}", id);

        return ResponseEntity
                .ok()
                .body(orderService.findById(id));
    }

    @GetMapping(value = "/order/date", produces = {"application/json"})
    public ResponseEntity<List<OrderDto>> getOrdersByDate(@RequestParam LocalDate date) {
        log.info("REST request to get Orders by date");

        return ResponseEntity
                .ok()
                .body(orderService.findByDate(date));
    }

    @GetMapping(value = "/order/product", produces = {"application/json"})
    public ResponseEntity<List<OrderDto>> getOrdersByProduct(@RequestParam Long productId) {
        log.info("REST request to get Orders by Product");

        return ResponseEntity
                .ok()
                .body(orderService.findByProduct(productId));
    }

    @GetMapping(value = "/order/customer", produces = {"application/json"})
    public ResponseEntity<List<OrderDto>> getOrdersByCustomer(@RequestParam Long customerId) {
        log.info("REST request to get Orders by Customer");

        return ResponseEntity
                .ok()
                .body(orderService.findByCustomer(customerId));
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Long id) {
        log.info("REST request to delete Order : {}", id);

        orderService.delete(id);
    }
}
