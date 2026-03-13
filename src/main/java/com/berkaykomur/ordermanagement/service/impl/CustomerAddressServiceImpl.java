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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        CustomerAddress customerAddress = customerAddressRepository.findByIdAndCustomerId(customerId,addressId)
                        .orElseThrow(()-> new ResourceNotFoundException("Address not found by id. "+addressId));
        addressMapper.updateEntity(address,customerAddress);
        return addressMapper.toResponse(customerAddressRepository.save(customerAddress));
    }

    @Transactional
    @Override
    public void deleteAddress(Long customerId,Long addressId) {
        CustomerAddress customerAddress=customerAddressRepository.findByIdAndCustomerId(customerId,addressId)
                        .orElseThrow(()->new ResourceNotFoundException("address not found by id. "+addressId));
        customerAddressRepository.delete(customerAddress);
    }

    @Override
    public Page<AddressResponse> getAllAddresses(Long customerId,Pageable pageable) {
        Page<CustomerAddress> customerAddressPage = customerAddressRepository.findAllByCustomerId(customerId,pageable);
        return customerAddressPage.map(addressMapper::toResponse);
    }


}
