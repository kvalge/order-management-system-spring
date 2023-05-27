package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    private String registrationCode;
    private String full_name;
    private String email;
    private String telephone;
}
