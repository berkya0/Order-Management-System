package com.berkaykomur.ordermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterRequest {
    @NotBlank
    @Size(min = 2,message = "First must be at least minimum 2 characters")
    private String firstName;
    @NotBlank
    @Size(min = 2,message = "First must be at least minimum 2 characters")
    private String lastName;
    @NotBlank
    @Size(min = 2,message = "Username must be at least minimum 2 characters")
    private String username;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(\\+90|0)?5[0-9]{9}$",
            message = "Please enter a valid Turkish phone number (e.g., 05xx xxx xx xx)"
    )
    private String phoneNumber;
    @NotBlank
    @Size(min =6,message = "Password must be at least minimum 6 characters")
    private String password;

    @NotBlank
    @Email(message = "Invalid e-mail format")
    private String email;
}
