package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper extends EntityMapper<Customer, CustomerDto>{

    Customer requestToEntity(CustomerRequest request);
}
