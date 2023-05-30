package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderLineRepositoryTest {

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    Product product = new Product();
    OrderLine orderLine = new OrderLine();

    @BeforeEach
    void setup() {
        product.setName("Product Name");
        product.setSkuCode("Sku Code");
        product.setUnitPrice(11.11F);
        
        orderLine.setQuantity(11);
        orderLine.setProduct(product);

        testEntityManager.persist(product);
        testEntityManager.persist(orderLine);
    }

    @Test
    void findAllByProductId() {
        List<OrderLine> orderLineList = orderLineRepository.findAllByProductId(product.getId());

        assertEquals(1, orderLineList.size());
        assertEquals("Product Name", orderLineList.get(0).getProduct().getName());;
    }
}