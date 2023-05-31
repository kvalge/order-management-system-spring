package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private String fullName;
    private String email;
    private String telephone;
}
