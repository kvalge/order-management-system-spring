package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private String password;
    private Long customerId;
}
