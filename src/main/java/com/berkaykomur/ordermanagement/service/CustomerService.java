package com.berkaykomur.ordermanagement.service;

import com.berkaykomur.ordermanagement.dto.CustomerRequest;
import com.berkaykomur.ordermanagement.dto.CustomerResponse;
import com.berkaykomur.ordermanagement.entity.Customer;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest request);


}
