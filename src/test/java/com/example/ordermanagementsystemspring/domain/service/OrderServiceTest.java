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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    List<Order> orders = new ArrayList<>();
    List<OrderDto> orderDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        customer.setId(1L);
        customer.setFullName("Customer Full Name");
        customer.setEmail("Customer email");
        customer.setTelephone("Customer telephone");

        order.setId(1L);
        order.setCustomer(customer);

        orders.add(order);

        request.setCustomerId(customer.getId());

        orderDto.setId(1L);
        orderDto.setCustomerId(customer.getId());

        orderDtos.add(orderDto);

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
        Mockito.doNothing().when(orderValidationService).ordersNotFound();
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDtoList(orders)).thenReturn(orderDtos);

        List<OrderDto> dtos = orderService.findAll();

        assertThat(dtos).isNotNull().isNotEmpty().hasSize(1);
        assertEquals(request.getCustomerId(), dtos.get(0).getCustomerId());
    }

    @Test
    void findById() {
        OrderDto dto = orderService.findById(order.getId());

        assertNotNull(dto);
        assertEquals(customer.getId(), dto.getCustomerId());
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