package com.berkaykomur.ordermanagement.utils;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;

public class TestDataUtil {

    public static AddressRequest createAddressRequest() {
        AddressRequest request = new AddressRequest();
        request.setProvince("İstanbul");
        request.setDistrict("Kadıköy");
        request.setNeighborhood("Moda");
        request.setStreet("Şair Nefi Sokak");
        request.setBuildingNo("15A");
        request.setApartmentNo("Daire: 4");
        request.setFullAddress("Moda Mah. Şair Nefi Sok. No:15A D:4 Kadıköy/İstanbul");
        request.setTitle("Ev Adresim");
        return request;
    }

    public static AddressResponse createAddressResponse() {
        AddressResponse response = new AddressResponse();
        response.setProvince("İstanbul");
        response.setDistrict("Kadıköy");
        response.setNeighborhood("Moda");
        response.setStreet("Şair Nefi Sokak");
        response.setBuildingNo("15A");
        response.setApartmentNo("Daire: 4");
        response.setTitle("Ev Adresim");
        return response;
    }
}
