package com.berkaykomur.ordermanagement.service.impl;

import com.berkaykomur.ordermanagement.dto.*;
import com.berkaykomur.ordermanagement.entity.Customer;
import com.berkaykomur.ordermanagement.entity.RefreshToken;
import com.berkaykomur.ordermanagement.entity.User;
import com.berkaykomur.ordermanagement.enums.CustomerStatus;
import com.berkaykomur.ordermanagement.enums.Role;
import com.berkaykomur.ordermanagement.exception.EmailAlreadyExitsException;
import com.berkaykomur.ordermanagement.exception.PasswordOrUsernameNotFound;
import com.berkaykomur.ordermanagement.exception.ResourceNotFoundException;
import com.berkaykomur.ordermanagement.exception.UsernameAlreadyExistsException;
import com.berkaykomur.ordermanagement.mapper.UserMapper;
import com.berkaykomur.ordermanagement.repository.RefreshTokenRepository;
import com.berkaykomur.ordermanagement.repository.UserRepository;
import com.berkaykomur.ordermanagement.service.AuthService;
import com.berkaykomur.ordermanagement.service.RefreshTokenService;
import com.berkaykomur.ordermanagement.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final CustomerMapper customerMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService  refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request){
        if(userRepository.existsUserByUsername(request.getUsername())){
            throw new UsernameAlreadyExistsException("Username already exists: "+request.getUsername());
        }
        if(userRepository.existsUserByEmail(request.getEmail())){
            throw new EmailAlreadyExitsException("Email already exists: "+request.getEmail());
        }
        User user=userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);

        Customer customer=customerMapper.toEntity(request);
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        user.setCustomer(customer);
        customer.setUser(user);

        userRepository.save(user);
        return userMapper.toRegisterResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        authenticationManager.authenticate(authToken);
        User user=userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new PasswordOrUsernameNotFound("Password or username is incorrect"));
        String accessToken= jwtUtil.generateToken(user);
        RefreshTokenResponse refreshToken=refreshTokenService.createRefreshToken(user);
        LoginResponse loginResponse=userMapper.toLoginResponse(user);
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken.toString());
        loginResponse.setLastLogin(LocalDateTime.now());
        return loginResponse;
    }
    @Transactional
    @Override
    public String logout(RefreshTokenRequest request) {
        RefreshToken refreshToken=refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(()->new ResourceNotFoundException("Refresh token not found"));
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
        return "logged out";
    }
    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken=refreshTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(()->new ResourceNotFoundException("Refresh token not found"));
        refreshTokenService.verifyRefreshToken(refreshToken);
        User user=refreshToken.getUser();
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);

        String accessToken=jwtUtil.generateToken(user);

        RefreshTokenResponse refreshTokenResponse=refreshTokenService.createRefreshToken(user);
        refreshTokenResponse.setAccessToken(accessToken);

        return refreshTokenResponse;
    }

}
