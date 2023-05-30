package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.ProductException;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductValidationService {

    @Resource
    private ProductRepository productRepository;

    public void productsNotFound() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductException("Products not found!");
        }
    }

    public void productNotFound(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ProductException("Product id " + id + " not found!");
        }
    }
}
