package com.berkaykomur.ordermanagement.mapper;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",
nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    CustomerAddress toEntity(AddressRequest address);
    AddressResponse toResponse(CustomerAddress address);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "customer",ignore = true)
    void updateEntity(AddressRequest address,@MappingTarget CustomerAddress customerAddress);
}
