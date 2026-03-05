package com.berkaykomur.ordermanagement.controller;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.mapper.AddressMapper;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import com.berkaykomur.ordermanagement.util.JwtUtil;
import com.berkaykomur.ordermanagement.utils.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.springframework.http.MediaType;
@WebMvcTest(CustomerAddressController.class)
@Import({JwtUtil.class})
class CustomerAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerAddressService customerAddressService;

    @Test
    @WithMockUser
    void shouldCreateAddressSuccessfully() throws Exception {
        Long customerId = 1L;
        AddressRequest addressRequest = TestDataUtil.createAddressRequest();
        AddressResponse addressResponse = TestDataUtil.createAddressResponse();
        when(customerAddressService.createAddress(eq(customerId), any(AddressRequest.class)))
                .thenReturn(addressResponse);
        mockMvc.perform(post("/api/address/customer/createCustomer/{customer_id}",customerId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldUpdateAddressSuccessfully() throws Exception {
        Long customerId=1L;
        Long addressId=2L;
        AddressRequest addressRequest= TestDataUtil.createAddressRequest();
        AddressResponse addressResponse= TestDataUtil.createAddressResponse();
        when(customerAddressService.updateAddress(eq(customerId),eq(addressId),any(AddressRequest.class)))
                .thenReturn(addressResponse);
        mockMvc.perform(put("/api/address/customer/updateCustomer/{customer_id}/address/{address_id}"
                        ,customerId, addressId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.province").value(addressResponse.getProvince()));
    }

    @Test
    @WithMockUser
    void shouldDeleteAddressSuccessfully() throws Exception {
        Long customerId=1L;
        Long addressId=2L;
        doNothing().when(customerAddressService).deleteAddress(eq(customerId),eq(addressId));
        mockMvc.perform(delete("/api/address/customer/deleteCustomer/{customer_id}/address/{address_id}"
                        ,customerId, addressId)
                        .with(csrf()))
                .andExpect(status().isOk());

    }
}