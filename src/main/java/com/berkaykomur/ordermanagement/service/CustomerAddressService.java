package com.berkaykomur.ordermanagement.service;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CustomerAddressService {

    AddressResponse createAddress(Long customerId, AddressRequest address);
    AddressResponse updateAddress(Long customerId,Long address_id, AddressRequest address);
    void deleteAddress(Long customerId, Long addressId);
    Page<AddressResponse> getAllAddresses(Long customerId,Pageable pageable);



}
