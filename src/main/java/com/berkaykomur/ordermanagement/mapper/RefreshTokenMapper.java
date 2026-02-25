package com.berkaykomur.ordermanagement.mapper;

import com.berkaykomur.ordermanagement.dto.RefreshTokenResponse;
import com.berkaykomur.ordermanagement.entity.RefreshToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenMapper {

    RefreshTokenResponse toRefreshTokenResponse(RefreshToken refreshToken);
}
