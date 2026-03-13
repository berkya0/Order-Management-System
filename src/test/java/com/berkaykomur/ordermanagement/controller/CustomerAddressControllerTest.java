package com.berkaykomur.ordermanagement.controller;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import com.berkaykomur.ordermanagement.util.JwtUtil;
import com.berkaykomur.ordermanagement.utils.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerAddressController.class)
@Import({JwtUtil.class})
class CustomerAddressControllerTest {

    //Düzenlenecek
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerAddressService customerAddressService;

    // Not: @AuthenticationPrincipal kullandığın için,
    // testte MockUser'ın ID'sini simüle etmen gerekebilir.
    @Test
    @WithMockUser(username = "1") // Basit bir yaklaşım, gerekirse CustomUser factory kullan
    void shouldCreateAddressSuccessfully() throws Exception {
        AddressRequest addressRequest = TestDataUtil.setAddressRequest();
        AddressResponse addressResponse = TestDataUtil.setAddressResponse();

        // Servis katmanında ID'nin 1L olarak geldiğini varsayıyoruz
        when(customerAddressService.createAddress(eq(1L), any(AddressRequest.class)))
                .thenReturn(addressResponse);

        mockMvc.perform(post("/api/address/customer/createAddress")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "1")
    void shouldUpdateAddressSuccessfully() throws Exception {
        Long addressId = 2L;
        AddressRequest addressRequest = TestDataUtil.setAddressRequest();
        AddressResponse addressResponse = TestDataUtil.setAddressResponse();

        when(customerAddressService.updateAddress(eq(1L), eq(addressId), any(AddressRequest.class)))
                .thenReturn(addressResponse);

        mockMvc.perform(put("/api/address/customer/update/address/{address_id}", addressId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.province").value(addressResponse.getProvince()));
    }

    @Test
    @WithMockUser(username = "1")
    void shouldDeleteAddressSuccessfully() throws Exception {
        Long addressId = 2L;

        doNothing().when(customerAddressService).deleteAddress(eq(1L), eq(addressId));

        mockMvc.perform(delete("/api/address/customer/delete/address/{address_id}", addressId)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}