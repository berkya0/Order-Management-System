package com.berkaykomur.ordermanagement.service;

import com.berkaykomur.ordermanagement.dto.*;
import com.berkaykomur.ordermanagement.dto.refreshToken.RefreshTokenRequest;
import com.berkaykomur.ordermanagement.dto.refreshToken.RefreshTokenResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    String logout(RefreshTokenRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
