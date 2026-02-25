package com.berkaykomur.ordermanagement.dto;

import com.berkaykomur.ordermanagement.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse extends BaseResponse {

    private Long id;
    private String username;
    private Role role;
    private String email;

}
