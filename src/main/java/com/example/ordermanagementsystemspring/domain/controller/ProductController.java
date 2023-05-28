package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.exception.ProductException;
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

    @PutMapping(value = "/product", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
        log.info("REST request to update Product");
        if (productDto == null) {
            throw new ProductException("Product data are missing");
        }

        return ResponseEntity
                .ok()
                .body(productService.update(productDto));
    }

    @PatchMapping(value = "/product/{id}", produces = {"application/json"}, consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<ProductDto> partialUpdateProduct(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody ProductDto productDto) {
        log.info("REST request to partial update Product");

        if (productDto == null) {
            throw new ProductException("Product data are missing");
        }
        productDto.setId(id);

        return ResponseEntity
                .ok()
                .body(productService.partialUpdate(productDto));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("REST request to delete Product : {}", id);
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
