package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.RoleService;
import com.example.ordermanagementsystemspring.domain.service.dto.RoleDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoleController.class)
@ExtendWith(SpringExtension.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoleService roleService;

    @Test
    @WithMockUser(roles = "USER")
    void createRole() throws Exception {
        String name = "USER";

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setName("USER");

        when(roleService.save(name)).thenReturn(roleDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/role")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .param("name", "USER"))
                .andExpect(status().isOk());
    }
}