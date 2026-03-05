package com.berkaykomur.ordermanagement.service.impl;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.entity.Customer;
import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import com.berkaykomur.ordermanagement.exception.ResourceNotFoundException;
import com.berkaykomur.ordermanagement.mapper.AddressMapper;
import com.berkaykomur.ordermanagement.repository.CustomerAddressRepository;
import com.berkaykomur.ordermanagement.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Optional;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerAddressImplTest {

    @InjectMocks
    private CustomerAddressServiceImpl addressServiceImpl;
    @Mock
    private CustomerAddressRepository customerAddressRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AddressMapper addressMapper;

    @Test
    void shouldCreateAddressSuccessfully() {
        Long customerId = 1L;

        Customer customer = new Customer();
        customer.setCustomerAddress(new  ArrayList<>());
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        CustomerAddress address = new CustomerAddress();
        AddressRequest addressRequest = new AddressRequest();
        AddressResponse addressResponse = new AddressResponse();

        when(addressMapper.toEntity(addressRequest)).thenReturn(address);
        when(customerAddressRepository.save(address)).thenReturn(address);
        when(addressMapper.toResponse(address)).thenReturn(addressResponse);
        AddressResponse result = addressServiceImpl.createAddress(customerId, addressRequest);
        assertEquals(addressResponse, result,"The expected response does not match the actual response");

        verify(customerRepository).findById(customerId);
        verify(addressMapper).toEntity(addressRequest);
        verify(customerAddressRepository).save(address);
        verify(addressMapper).toResponse(address);

    }
    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        Long customerId = 99L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        AddressRequest addressRequest = new AddressRequest();
        ResourceNotFoundException ex= assertThrows(ResourceNotFoundException.class,()-> addressServiceImpl.createAddress(customerId,addressRequest));
        assertEquals("Customer could not found by id. "+customerId,ex.getMessage());
        verify(customerRepository).findById(customerId);
        verifyNoInteractions(addressMapper);
        verifyNoInteractions(customerAddressRepository);
    }
    @Test
    void ifCustomerAddressIsNullReturnTrueStatement(){
        Long customerId = 1L;
        Customer customer=new Customer();
        customer.setId(customerId);
        customer.setCustomerAddress(null);

        AddressRequest addressRequest = new AddressRequest();
        AddressResponse addressResponse = new AddressResponse();
        CustomerAddress address = new CustomerAddress();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(addressMapper.toEntity(addressRequest)).thenReturn(address);
        when(customerAddressRepository.save(any(CustomerAddress.class))).thenReturn(address);
        when(addressMapper.toResponse(address)).thenReturn(addressResponse);

        addressServiceImpl.createAddress(customerId, addressRequest);
        assertNotNull(customer.getCustomerAddress(),"Customer address list should not be null");
        assertEquals(1,customer.getCustomerAddress().size(),"Address list should contain 1 element");
        assertTrue(customer.getCustomerAddress().contains(address),"Returned address response did not match expected");

        verify(customerRepository).findById(customerId);
        verify(addressMapper).toEntity(addressRequest);
        verify(customerAddressRepository).save(address);
        verify(addressMapper).toResponse(address);



    }


    @Test
    void shouldUpdateAddressSuccessfully() {
        Long customerId = 1L;
        Long addressId = 2L;

        Customer customer = new Customer();
        CustomerAddress customerAddress = new CustomerAddress();
        customer.setId(customerId);
        customerAddress.setCustomer(customer);

        when(customerAddressRepository.findById(addressId)).thenReturn(Optional.of(customerAddress));

        assertEquals(customerId,customerAddress.getCustomer().getId(),"The customer does not match the actual address");

        AddressRequest addressRequest = new AddressRequest();
        doNothing().when(addressMapper).updateEntity(addressRequest,customerAddress);
        AddressResponse addressResponse =new AddressResponse();

        when(addressMapper.toResponse(customerAddress)).thenReturn(addressResponse);

        when(customerAddressRepository.save(customerAddress)).thenReturn(customerAddress);
        AddressResponse response=addressServiceImpl.updateAddress(customerId,addressId,addressRequest);
        assertEquals(addressResponse,response);

        verify(customerAddressRepository).findById(addressId);
        verify(customerAddressRepository).save(customerAddress);
        verify(addressMapper).toResponse(customerAddress);

    }
    @Test
    void shouldThrowExceptionWhenCustomerAddressNotFound() {
        Long customerId = 99L;
        Long addressId = 2L;
        AddressRequest addressRequest = new AddressRequest();
        when(customerAddressRepository.findById(addressId)).thenReturn(Optional.empty());
        ResourceNotFoundException ex=assertThrows(ResourceNotFoundException.class,
                ()-> addressServiceImpl.updateAddress(customerId,addressId,addressRequest));
        assertEquals("address not found",ex.getMessage());
        verify(customerAddressRepository).findById(addressId);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(addressMapper);
    }

    @Test
    void souhldThrowExceptionWhenCustomerAddressDoesNotBelongToCustomer() {
        Long addressId = 2L;
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(99L);
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(customer);
        AddressRequest addressRequest = new AddressRequest();
        when(customerAddressRepository.findById(addressId)).thenReturn(Optional.of(customerAddress));
        ResourceNotFoundException ex=assertThrows(ResourceNotFoundException.class,
                ()->addressServiceImpl.updateAddress(customerId,addressId,addressRequest));
        assertEquals("this address does not belong to this customer",ex.getMessage());
        verify(customerAddressRepository).findById(addressId);
        verifyNoInteractions(addressMapper);
        verifyNoInteractions(customerRepository);
    }
    @Test
    void deleteAddressSuccessfully() {
        Long customerId = 1L;
        Long addressId = 2L;

        Customer customer = new Customer();
        customer.setId(customerId);
        CustomerAddress address = new CustomerAddress();
        address.setCustomer(customer);

        when(customerAddressRepository.findById(addressId))
                .thenReturn(Optional.of(address));

        addressServiceImpl.deleteAddress(customerId, addressId);

        verify(customerAddressRepository).deleteById(addressId);

    }
    @Test
    void shouldThrowExceptionWhenAddressNotFound() {
        Long addressId = 2L;
        Long customerId=3L;

        when(customerAddressRepository.findById(addressId)).thenReturn(Optional.empty());
        ResourceNotFoundException ex=assertThrows(ResourceNotFoundException.class,
                ()->addressServiceImpl.deleteAddress(customerId,addressId));
        assertEquals("address not found",ex.getMessage());
        verify(customerAddressRepository).findById(addressId);
        verifyNoInteractions(addressMapper);
        verifyNoInteractions(customerRepository);


    }
    @Test
    void shouldThrowExceptionWhenCustomerAddressDoesNotBelongToCustomer() {
        Long customerId = 1L;
        Long addressId = 2L;
        Customer customer = new Customer();
        customer.setId(customerId);
        CustomerAddress address = new CustomerAddress();
        address.setCustomer(customer);
        address.getCustomer().setId(99L);

        when(customerAddressRepository.findById(addressId)).thenReturn(Optional.of(address));
        ResourceNotFoundException ex=assertThrows(ResourceNotFoundException.class,
                ()->addressServiceImpl.deleteAddress(customerId,addressId));
        assertEquals("this address does not belong to this customer",ex.getMessage());
        verify(customerAddressRepository).findById(addressId);
        verify(customerAddressRepository, never()).deleteById(anyLong());
        verifyNoInteractions(addressMapper);
        verifyNoInteractions(customerRepository);

    }


}