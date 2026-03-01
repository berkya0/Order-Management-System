package com.berkaykomur.ordermanagement.dto;

import lombok.Getter;

@Getter
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private CustomerStatus status;

}
