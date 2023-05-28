package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.ProductMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProductService {

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductRepository productRepository;

    /**
     * Product inserted price is given in cents, returned price is calculated to euros.
     */
    public ProductDto save(ProductDto productDto) {
        log.info("Request to save Product : {}", productDto);

        Product product = productMapper.toEntity(productDto);
        product.setSkuCode(UUID.randomUUID().toString());
        product = productRepository.save(product);
        float price = (float) Math.round((productDto.getUnitPrice() / 100) * 100) / 100;
        product.setUnitPrice (price);

        return productMapper.toDto(product);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return productMapper.toDtoList(products);
    }
}
