package com.berkaykomur.ordermanagement.dto.customerAdress;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class AddressRequest {
    @NotBlank
    private String province;

    @NotBlank
    private String district;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String street;

    @NotBlank
    private String buildingNo;

    @NotBlank
    private String apartmentNo;

    private String title;
}
