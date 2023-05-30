package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderLineRepositoryTest {

    @Autowired
    private OrderLineRepository orderLineRepository;

    Product product = new Product();

    @BeforeEach
    void setup() {
        product.setId(6L);
        product.setName("Product Name");
        product.setSkuCode("Sku Code");
        product.setUnitPrice(11.11F);
    }

    @Test
    void findAllByProductId() {
        List<OrderLine> orderLines = orderLineRepository.findAllByProductId(product.getId());

        assertNotNull(orderLines);
    }
}