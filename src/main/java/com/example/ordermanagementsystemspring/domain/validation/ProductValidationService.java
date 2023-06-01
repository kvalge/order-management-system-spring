package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.CustomerException;
import com.example.ordermanagementsystemspring.domain.exception.ProductException;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductValidationService {

    @Resource
    private ProductRepository productRepository;

    public void productDataNotFound(ProductRequest request) {
        if (request.getName() == null || request.getUnitPrice() == null) {
            throw new CustomerException("Product data not found!");
        }
    }

    public void productDtoDataNotFound(ProductDto productDto) {
        if (productDto.getId() == null ||
                productDto.getSkuCode() == null ||
                productDto.getName() == null ||
                productDto.getUnitPrice() == null) {
            throw new CustomerException("Product data not found!");
        }
    }

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
