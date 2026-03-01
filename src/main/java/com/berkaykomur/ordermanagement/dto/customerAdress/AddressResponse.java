package com.berkaykomur.ordermanagement.dto.customerAdress;

import com.berkaykomur.ordermanagement.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse extends BaseResponse {

    private String province;

    private String district;

    private String neighborhood;

    private String street;

    private String buildingNo;

    private String apartmentNo;

    private String title;
}
