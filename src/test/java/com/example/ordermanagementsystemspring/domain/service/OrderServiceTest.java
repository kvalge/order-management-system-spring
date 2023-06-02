package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.repository.CustomerRepository;
import com.example.ordermanagementsystemspring.domain.repository.OrderRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.OrderMapper;
import com.example.ordermanagementsystemspring.domain.validation.CustomerValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderLineValidationService;
import com.example.ordermanagementsystemspring.domain.validation.OrderValidationService;
import com.example.ordermanagementsystemspring.domain.validation.ProductValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderLineService orderLineService;

    @Mock
    private OrderLineValidationService orderLineValidationService;

    @Mock
    private OrderValidationService orderValidationService;

    @Mock
    private ProductValidationService productValidationService;

    @Mock
    private CustomerValidationService customerValidationService;

    Order order = new Order();

    OrderRequest request = new OrderRequest();

    OrderDto orderDto = new OrderDto();

    Customer customer = new Customer();

    @BeforeEach
    void setUp() {
        customer.setId(1L);
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer email");
        customer.setTelephone("Customer telephone");

        order.setId(1L);
        order.setCustomer(customer);

        request.setCustomerId(customer.getId());

        orderDto.setId(1L);
        orderDto.setCustomerId(customer.getId());

        Mockito.doNothing().when(orderValidationService).orderNotFound(anyLong());
        when(orderRepository.findById(anyLong())).thenReturn(Optional.ofNullable(order));
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);
    }

    @Test
    void save() {
        when(orderMapper.requestToOrder(request)).thenReturn(order);
        when(customerRepository.findById(request.getCustomerId())).thenReturn(Optional.ofNullable(customer));

        OrderDto dto = orderService.save(request);

        assertNotNull(dto);
        assertEquals(request.getCustomerId(), dto.getCustomerId());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByDate() {
    }

    @Test
    void findByProduct() {
    }

    @Test
    void findByCustomer() {
    }

    @Test
    void delete() {
    }
}