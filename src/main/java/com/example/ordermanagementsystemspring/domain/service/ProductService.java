package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.exception.ExceptionCodes;
import com.example.ordermanagementsystemspring.domain.exception.ProductException;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.ProductMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
     * Inserted price of the product is given in cents, returned price is calculated to euros.
     */
    public ProductDto save(ProductDto productDto) {
        log.info("Request to save Product : {}", productDto);

        Product product = productMapper.toEntity(productDto);
        product.setSkuCode(UUID.randomUUID().toString());
        product = productRepository.save(product);
        float price = (float) Math.round((productDto.getUnitPrice() / 100) * 100) / 100;
        product.setUnitPrice(price);

        return productMapper.toDto(product);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();

        return productMapper.toDtoList(products);
    }

    public ProductDto findById(Long id) {
        log.info("Request to find Product by id : {}", id);

        Optional<Product> product =
                Optional.ofNullable(productRepository
                        .findById(id)
                        .orElseThrow(() -> new ProductException(
                                "Product #" + id + " not found", ExceptionCodes.PRODUCT_NOT_FOUND)));

        return productMapper.toDto(product.get());
    }

    /**
     * Updated price of the product is given in cents, saved price is calculated to euros.
     */
    public ProductDto update(ProductDto productDto) {
        log.info("Request to update Product : {}", productDto);

        Product product = productRepository
                .findById(productDto.getId())
                .orElseThrow(() -> new ProductException("Product #" + productDto.getId() + " not found"));
        productMapper.update(product, productDto);
        float price = (float) Math.round((product.getUnitPrice() / 100) * 100) / 100;
        product.setUnitPrice(price);
        productRepository.save(product);

        return productMapper.toDto(product);
    }

    /**
     * Updated price of the product is given in cents, saved price is calculated to euros.
     */
    public ProductDto partialUpdate(ProductDto productDto) {
        log.info("Request to partially update Product : {}", productDto);

        Product product = productRepository
                .findById(productDto.getId())
                .orElseThrow(() -> new ProductException("Product #" + productDto.getId() + " not found"));
        productMapper.partialUpdate(product, productDto);
        float price = (float) Math.round((product.getUnitPrice() / 100) * 100) / 100;
        product.setUnitPrice(price);
        productRepository.save(product);

        return productMapper.toDto(product);
    }
}
