package com.berkaykomur.ordermanagement.service;

import com.berkaykomur.ordermanagement.dto.RefreshTokenResponse;
import com.berkaykomur.ordermanagement.entity.RefreshToken;
import com.berkaykomur.ordermanagement.entity.User;

public interface RefreshTokenService {

    RefreshTokenResponse createRefreshToken(User user);
    RefreshToken verifyRefreshToken(RefreshToken refreshToken);

}
