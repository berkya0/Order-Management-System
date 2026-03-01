package com.berkaykomur.ordermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CustomerRequest {

//    @Size(min = 2,message = "Name must be at least 2 characters")
//    private String firstName;
//
//    @Size(min = 2,message = "Last name must be at least 2 characters")
//    private String lastName;
//
//    @NotBlank(message = "E-mail cannot be blank")
//    @Email(message = "Invalid e-mail format")
//    private String email;
//
//    @NotBlank(message = "Phone number is required")
//    @Pattern(
//            regexp = "^(\\+90|0)?5[0-9]{9}$",
//            message = "Please enter a valid Turkish phone number (e.g., 05xx xxx xx xx)"
//    )
//    private String phoneNumber;

}
