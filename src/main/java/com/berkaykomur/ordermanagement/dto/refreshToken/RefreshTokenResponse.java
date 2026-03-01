package com.berkaykomur.ordermanagement.dto.refreshToken;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiry;
}
