package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Product;
import com.example.ordermanagementsystemspring.domain.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper extends EntityMapper<Product, ProductDto> {
}
