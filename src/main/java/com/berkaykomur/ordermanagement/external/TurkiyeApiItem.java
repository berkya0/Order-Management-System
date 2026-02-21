package com.berkaykomur.ordermanagement.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TurkiyeApiItem {

    @JsonProperty("il_name")
    private String province;

    @JsonProperty("ilce_name")
    private String district;

    @JsonProperty("mahalle_name")
    private String neighborhood;

    @JsonProperty("sokak_name")
    private String street;
}
