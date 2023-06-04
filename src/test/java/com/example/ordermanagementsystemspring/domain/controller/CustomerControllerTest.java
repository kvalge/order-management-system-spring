package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.CustomerService;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
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

@WebMvcTest(controllers = CustomerController.class)
@WithMockUser(username = "user", roles = "USER")
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    CustomerRequest request = new CustomerRequest();
    CustomerDto customerDto = new CustomerDto();
    List<CustomerDto> customerDtos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        request.setFullName("Full Name");
        request.setEmail("Customer email");
        request.setTelephone("Customer telephone");

        customerDto.setId(1L);
        customerDto.setFullName("Full Name");
        customerDto.setEmail("Customer email");
        customerDto.setTelephone("Customer telephone");

        customerDtos.add(customerDto);
    }

    @Test
    void createCustomer() throws Exception {
        given(customerService.save(request)).willReturn(customerDto);

        ResultActions response = mockMvc.perform(post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(customerDto.getFullName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getAllCustomers() throws Exception {
        when(customerService.findAll()).thenReturn(customerDtos);

        ResultActions response = mockMvc.perform(get("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(objectMapper.writeValueAsString(customerDtos)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getCustomerById() throws Exception {
        when(customerService.findById(1L)).thenReturn(customerDto);

        ResultActions response = mockMvc.perform(get("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(customerDto.getFullName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateCustomer() throws Exception {
        when(customerService.update(customerDto)).thenReturn(customerDto);

        ResultActions response = mockMvc.perform(put("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(customerDto.getFullName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void partialUpdateCustomer() throws Exception {
        when(customerService.partialUpdate(customerDto)).thenReturn(customerDto);

        ResultActions response = mockMvc.perform(patch("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .content(objectMapper.writeValueAsString(customerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", CoreMatchers.is(customerDto.getFullName())))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void deleteCustomer() throws Exception {
        doNothing().when(customerService).delete(1L);

        ResultActions response = mockMvc.perform(delete("/api/customer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf()));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}