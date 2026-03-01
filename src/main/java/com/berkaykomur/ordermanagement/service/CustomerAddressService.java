package com.berkaykomur.ordermanagement.service;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;

public interface CustomerAddressService {

    AddressResponse createAddress(Long customer_id, AddressRequest address);
    AddressResponse updateAddress(Long customer_id,Long address_id, AddressRequest address);
    void deleteAddress(Long customer_id, Long address_id);


}
