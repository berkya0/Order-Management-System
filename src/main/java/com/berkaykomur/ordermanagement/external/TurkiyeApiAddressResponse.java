package com.berkaykomur.ordermanagement.external;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TurkiyeApiAddressResponse {

    private Boolean success;
    private List<TurkiyeApiItem> data;

}
