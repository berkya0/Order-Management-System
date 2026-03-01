package com.berkaykomur.ordermanagement.dto.refreshToken;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    @NotBlank(message = "Refresh token can not be blank")
    private String refreshToken;
}
