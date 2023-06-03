package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Long customerId;
}
