package com.berkaykomur.ordermanagement.controller;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.security.CustomUserDetails;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address/customer")
@RequiredArgsConstructor
public class CustomerAddressController {
    private final CustomerAddressService customerAddressService;
    @PostMapping("/createAddress")
    public ResponseEntity<AddressResponse> createAddress(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @Valid @RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(customerAddressService.createAddress(currentUser.getId(), addressRequest));
    }
    @PutMapping("/update/{address_id}")
    public ResponseEntity<AddressResponse> updateAddress(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                         @PathVariable Long address_id,
                                                         @Valid @RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok(customerAddressService.updateAddress(currentUser.getId(), address_id, addressRequest));
    }
    @DeleteMapping("/delete/{address_id}")
    public ResponseEntity<String> deleteAddress(@AuthenticationPrincipal CustomUserDetails currentUser,
                                                @PathVariable Long address_id){
        customerAddressService.deleteAddress(currentUser.getId(), address_id);
        return ResponseEntity.ok("Customer address deleted successfully");
    }
    @GetMapping("/addressList")
    public ResponseEntity<Page<AddressResponse>> findAllAddress(@AuthenticationPrincipal CustomUserDetails currentUser
                                                                ,Pageable pageable){
        return ResponseEntity.ok(customerAddressService.getAllAddresses(currentUser.getId(),pageable));
    }

}
