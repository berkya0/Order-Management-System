package com.berkaykomur.ordermanagement.dto.customerAdress;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @NotBlank
    private String fullAddress;
    @NotBlank
    private String title;
}
