package com.berkaykomur.ordermanagement.dto;

import com.berkaykomur.ordermanagement.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginResponse extends BaseResponse {

    private String username;
    private Role role;
    private String email;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime lastLogin;

}
