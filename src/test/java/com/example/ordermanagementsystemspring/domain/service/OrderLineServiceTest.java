package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.model.OrderLine;
import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.repository.OrderLineRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.repository.ProductRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderLineMapper;
import com.example.ordermanagementsystemspring.domain.validation.OrderLineValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderValidationService;
import com.example.ordermanagementsystemspring.domain.validation.ProductValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderLineServiceTest {

    @InjectMocks
    private OrderLineService orderLineService;

    @Mock
    private OrderLineRepository orderLineRepository;

    @Mock
    private OrderLineMapper orderLineMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderLineValidationService orderLineValidationService;

    @Mock
    private ProductValidationService productValidationService;

    @Mock
    private OrderValidationService orderValidationService;

    OrderLine orderLine = new OrderLine();
    OrderLineRequest request = new OrderLineRequest();
    OrderLineDto orderLineDto = new OrderLineDto();

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product Name");
        product.setUnitPrice(11.11F);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer email");
        customer.setTelephone("Customer telephone");

        Order order = new Order();
        order.setId(1L);
        order.setSubmissionDate(LocalDate.ofEpochDay(2023-06-02));
        order.setCustomer(customer);

        orderLine.setId(1L);
        orderLine.setQuantity(11);
        orderLine.setProduct(product);
        orderLine.setOrder(order);

        request.setQuantity(11);

        orderLineDto.setId(1L);
        orderLineDto.setQuantity(11);

        when(orderLineRepository.findById(anyLong())).thenReturn(Optional.ofNullable(orderLine));
        when(orderLineRepository.save(Mockito.any(OrderLine.class))).thenReturn(orderLine);
        when(orderLineMapper.toDto(orderLine)).thenReturn(orderLineDto);
    }
    @Test
    void save() {
        Mockito.doNothing().when(orderLineValidationService).orderLineDataNotFound(request);
        Mockito.doNothing().when(productValidationService).productNotFound(request.getProductId());
        Mockito.doNothing().when(orderValidationService).orderNotFound(request.getOrderId());
        when(orderLineMapper.requestToEntity(request)).thenReturn(orderLine);

        OrderLineDto dto = orderLineService.save(request);

        assertNotNull(dto);
        assertEquals(orderLineDto.getQuantity(), dto.getQuantity());
    }

    @Test
    void findByProduct() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}