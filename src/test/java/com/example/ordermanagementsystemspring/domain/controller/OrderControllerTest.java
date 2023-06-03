package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.OrderService;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    OrderRequest request = new OrderRequest();
    OrderDto orderDto = new OrderDto();
    List<OrderDto> orderDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        request.setCustomerId(1L);

        orderDto.setId(1L);
        orderDto.setCustomerId(1L);

        orderDtos.add(orderDto);
    }

    @Test
    void createOrder() throws Exception {
        given(orderService.save(request)).willReturn(orderDto);

        ResultActions response = mockMvc.perform(post("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllOrders() throws Exception {
        when(orderService.findAll()).thenReturn(orderDtos);

        ResultActions response = mockMvc.perform(get("/api/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrderById() throws Exception {
        when(orderService.findById(1L)).thenReturn(orderDto);

        ResultActions response = mockMvc.perform(get("/api/order/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrdersByDate() throws Exception {
        when(orderService.findByDate(LocalDate.ofEpochDay(2023 - 06 - 02))).thenReturn(orderDtos);

        ResultActions response = mockMvc.perform(get("/api/order/date")
                .contentType(MediaType.APPLICATION_JSON)
                .param("date", "2023-06-02")
                .content(objectMapper.writeValueAsString(orderDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrdersByProduct() throws Exception {
        when(orderService.findByProduct(1L)).thenReturn(orderDtos);

        ResultActions response = mockMvc.perform(get("/api/order/product")
                .contentType(MediaType.APPLICATION_JSON)
                .param("productId", "1")
                .content(objectMapper.writeValueAsString(orderDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrdersByCustomer() throws Exception {
        when(orderService.findByCustomer(1L)).thenReturn(orderDtos);

        ResultActions response = mockMvc.perform(get("/api/order/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .param("customerId", "1")
                .content(objectMapper.writeValueAsString(orderDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteOrder() throws Exception {
        doNothing().when(orderService).delete(1L);

        ResultActions response = mockMvc.perform(delete("/api/order/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}