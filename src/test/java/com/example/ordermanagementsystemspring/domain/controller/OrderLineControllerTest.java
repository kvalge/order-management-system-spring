package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.OrderLineService;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderLineRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderLineController.class)
class OrderLineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderLineService orderLineService;

    @Autowired
    private ObjectMapper objectMapper;

    OrderLineRequest request = new OrderLineRequest();
    OrderLineDto orderLineDto = new OrderLineDto();
    List<OrderLineDto> orderLineDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        request.setQuantity(11);
        request.setProductId(1L);
        request.setOrderId(1L);

        orderLineDto.setId(1L);
        orderLineDto.setQuantity(11);
        orderLineDto.setProductId(1L);
        orderLineDto.setOrderId(1L);

        orderLineDtos.add(orderLineDto);
    }

    @Test
    void createOrderLine() throws Exception {
        given(orderLineService.save(request)).willReturn(orderLineDto);

        ResultActions response = mockMvc.perform(post("/api/ordeline")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderLineDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", CoreMatchers.is(orderLineDto.getQuantity())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getOrderLinesByProduct() throws Exception {
        when(orderLineService.findByProduct(1L)).thenReturn(orderLineDtos);

        ResultActions response = mockMvc.perform(get("/api/orderline/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderLineDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateOrderLine() throws Exception {
        when(orderLineService.update(orderLineDto)).thenReturn(orderLineDto);

        ResultActions response = mockMvc.perform(put("/api/orderline")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderLineDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", CoreMatchers.is(orderLineDto.getQuantity())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteOrderLine() throws Exception {
        doNothing().when(orderLineService).delete(1L);

        ResultActions response = mockMvc.perform(delete("/api/orderline/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}