package com.berkaykomur.ordermanagement.service.impl;

import com.berkaykomur.ordermanagement.dto.refreshToken.RefreshTokenResponse;
import com.berkaykomur.ordermanagement.entity.RefreshToken;
import com.berkaykomur.ordermanagement.entity.User;
import com.berkaykomur.ordermanagement.exception.RefreshTokenExpiredException;
import com.berkaykomur.ordermanagement.mapper.RefreshTokenMapper;
import com.berkaykomur.ordermanagement.repository.RefreshTokenRepository;
import com.berkaykomur.ordermanagement.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public RefreshTokenResponse createRefreshToken(User user) {
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(refreshTokenExpiration));
        refreshToken.setUser(user);
        refreshTokenRepository.save(refreshToken);
        return refreshTokenMapper.toRefreshTokenResponse(refreshToken);
    }

    @Override
    public RefreshToken verifyRefreshToken(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshToken.setRevoked(true);
            refreshTokenRepository.save(refreshToken);
            throw new RefreshTokenExpiredException("Refresh token expired");
        }
        return refreshToken;
    }

}
