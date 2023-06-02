package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.ProductService;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductRequest;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    ProductRequest request = new ProductRequest();
    ProductDto productDto = new ProductDto();
    List<ProductDto> productDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        request.setName("Product Name");
        request.setUnitPrice(11.11F);

        productDto.setId(1L);
        productDto.setName("Product Name");
        productDto.setUnitPrice(11.11F);

        productDtos.add(productDto);
    }

    @Test
    void createProduct() throws Exception {
        given(productService.save(request)).willReturn(productDto);

        ResultActions response = mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.findAll()).thenReturn(productDtos);

        ResultActions response = mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getProductById() throws Exception {
        when(productService.findById(1L)).thenReturn(productDto);

        ResultActions response = mockMvc.perform(get("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateProduct() throws Exception {
        when(productService.update(productDto)).thenReturn(productDto);

        ResultActions response = mockMvc.perform(put("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void partialUpdateProduct() throws Exception {
        when(productService.partialUpdate(productDto)).thenReturn(productDto);

        ResultActions response = mockMvc.perform(patch("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDto.getName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteProduct() {
    }
}