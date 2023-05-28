package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.ProductService;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
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
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping(value = "/product", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("REST request to createProduct Product");
        return ResponseEntity
                .ok()
                .body(productService.save(productDto));
    }
}
