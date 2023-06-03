package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.AppException;
import com.example.ordermanagementsystemspring.domain.exception.ProductException;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductValidationServiceTest {

    @Autowired
    private ProductValidationService validationService;

    @Test
    void productDataNotFound() {

        assertThatThrownBy(() ->
        {
            throw new ProductException("Product data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Product data not found!");

        assertThrows(ProductException.class, () -> {
            ProductRequest request = new ProductRequest();
            validationService.productDataNotFound(request);
        });
    }

    @Test
    void productDtoDataNotFound() {
        assertThatThrownBy(() ->
        {
            throw new ProductException("Product data not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Product data not found!");
    }

    @Test
    void productsNotFound() {
        assertThatThrownBy(() ->
        {
            throw new ProductException("Products not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Products not found!");
    }

    @Test
    void productNotFound() {
        long id = 1;

        assertThatThrownBy(() ->
        {
            throw new ProductException("Product id " + id + " not found!");
        })
                .isInstanceOf(AppException.class)
                .hasMessageContaining("Product id " + id + " not found!");
    }
}