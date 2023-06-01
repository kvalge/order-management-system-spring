package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private String skuCode;
    private Float unitPrice;
}
