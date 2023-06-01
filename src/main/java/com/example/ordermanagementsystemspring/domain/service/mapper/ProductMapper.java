package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        imports = UUID.class)
public interface ProductMapper extends EntityMapper<Product, ProductDto> {

    @Mapping(expression = "java(UUID.randomUUID().toString())", target = ("skuCode"))
    Product requestToEntity(ProductRequest request);
}
