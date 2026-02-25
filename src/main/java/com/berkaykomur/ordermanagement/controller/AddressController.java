package com.berkaykomur.ordermanagement.controller;

import com.berkaykomur.ordermanagement.external.TurkiyeApiService;
import com.berkaykomur.ordermanagement.external.TurkiyeApiAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final TurkiyeApiService turkiyeApiService;

    @GetMapping("/search")
    public ResponseEntity<TurkiyeApiAddressResponse> search(@RequestParam String query){
        return ResponseEntity.ok(turkiyeApiService.search(query));
    }
}
