package com.berkaykomur.ordermanagement.controller;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address/customer")
@RequiredArgsConstructor
public class CustomerAddressController {
    private final CustomerAddressService customerAddressService;
    @PostMapping("/createCustomer/{customer_id}")
    public ResponseEntity<AddressResponse> createAddress(@PathVariable Long customer_id,
                                                         @Valid @RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(customerAddressService.createAddress(customer_id, addressRequest));
    }
    @PutMapping("/updateCustomer/{customer_id}/address/{address_id}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long customer_id, @PathVariable Long address_id,
                                                         @Valid @RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(customerAddressService.updateAddress(customer_id, address_id, addressRequest));
    }
    @DeleteMapping("/deleteCustomer/{customer_id}/address/{address_id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long customer_id, @PathVariable Long address_id){
        customerAddressService.deleteAddress(customer_id, address_id);
        return ResponseEntity.ok("Customer address deleted successfully");
    }

}
