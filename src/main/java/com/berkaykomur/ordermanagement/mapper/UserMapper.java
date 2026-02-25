package com.berkaykomur.ordermanagement.mapper;

import com.berkaykomur.ordermanagement.dto.LoginRequest;
import com.berkaykomur.ordermanagement.dto.LoginResponse;
import com.berkaykomur.ordermanagement.dto.RegisterRequest;
import com.berkaykomur.ordermanagement.dto.RegisterResponse;
import com.berkaykomur.ordermanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toEntity(RegisterRequest request);
    RegisterResponse toRegisterResponse(User user);
    LoginResponse toLoginResponse(User user);
}
