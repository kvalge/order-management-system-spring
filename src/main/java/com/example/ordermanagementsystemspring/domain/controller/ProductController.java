package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.ProductService;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping(value = "/product", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("REST request to create Product");
        return ResponseEntity
                .ok()
                .body(productService.save(productDto));
    }

    @GetMapping(value = "/product", produces = {"application/json"})
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("REST request to get all Products");
        return ResponseEntity
                .ok()
                .body(productService.findAll());
    }

    @GetMapping(value = "/product/{id}", produces = {"application/json"})
    public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "id", required = true) final Long id) {
        log.info("REST request to get Product : {}", id);
        return ResponseEntity
                .ok()
                .body(productService.findById(id));
    }
}
