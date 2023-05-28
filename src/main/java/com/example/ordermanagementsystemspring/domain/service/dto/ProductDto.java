package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String skuCode;
    private Float unitPrice;
}
