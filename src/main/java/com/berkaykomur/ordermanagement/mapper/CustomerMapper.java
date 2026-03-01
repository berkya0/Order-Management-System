package com.berkaykomur.ordermanagement.mapper;

import com.berkaykomur.ordermanagement.dto.CustomerResponse;
import com.berkaykomur.ordermanagement.dto.RegisterRequest;
import com.berkaykomur.ordermanagement.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);
    Customer toEntity(RegisterRequest request);
}
