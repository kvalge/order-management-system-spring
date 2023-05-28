package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.OrderLineService;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderLineController {

    @Resource
    private OrderLineService orderLineService;

    @PostMapping(value = "/ordeline", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<OrderLineDto> createOrderLine(@RequestBody OrderLineDto orderLineDto) {
        log.info("REST request to create Order Line");

        return ResponseEntity
                .ok()
                .body(orderLineService.save(orderLineDto));
    }
}
