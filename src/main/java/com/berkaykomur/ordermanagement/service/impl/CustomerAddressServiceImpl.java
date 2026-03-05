package com.berkaykomur.ordermanagement.service.impl;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.entity.Customer;
import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import com.berkaykomur.ordermanagement.exception.ResourceNotFoundException;
import com.berkaykomur.ordermanagement.mapper.AddressMapper;
import com.berkaykomur.ordermanagement.repository.CustomerAddressRepository;
import com.berkaykomur.ordermanagement.repository.CustomerRepository;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerAddressServiceImpl implements CustomerAddressService {
    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;

    @Transactional
    @Override
    public AddressResponse createAddress(Long id, AddressRequest address) {
        Customer customer =customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer could not found by id. "+id));
        CustomerAddress customerAddress = addressMapper.toEntity(address);
        customerAddress.setCustomer(customer);
        if (customer.getCustomerAddress() == null) {
            customer.setCustomerAddress(new ArrayList<>());
        }
        customer.getCustomerAddress().add(customerAddress);
        return addressMapper.toResponse(customerAddressRepository.save(customerAddress));
    }

    @Transactional
    @Override
    public AddressResponse updateAddress(Long customerId,Long addressId, AddressRequest address) {
        CustomerAddress customerAddress = customerAddressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("address not found"));
        if(!customerAddress.getCustomer().getId().equals(customerId)) {
            throw new ResourceNotFoundException("this address does not belong to this customer");
        }
        addressMapper.updateEntity(address,customerAddress);
        return addressMapper.toResponse(customerAddressRepository.save(customerAddress));
    }

    @Transactional
    @Override
    public void deleteAddress(Long customerId,Long addressId) {
        CustomerAddress customerAddress=customerAddressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("address not found"));
        if(!customerAddress.getCustomer().getId().equals(customerId)) {
            throw new ResourceNotFoundException("this address does not belong to this customer");
        }
        customerAddressRepository.deleteById(addressId);
    }


}
