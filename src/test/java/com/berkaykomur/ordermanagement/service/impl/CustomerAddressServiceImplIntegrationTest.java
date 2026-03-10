package com.berkaykomur.ordermanagement.service.impl;

import com.berkaykomur.ordermanagement.dto.customerAdress.AddressRequest;
import com.berkaykomur.ordermanagement.dto.customerAdress.AddressResponse;
import com.berkaykomur.ordermanagement.entity.Customer;
import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import com.berkaykomur.ordermanagement.entity.User;
import com.berkaykomur.ordermanagement.enums.Role;
import com.berkaykomur.ordermanagement.exception.ResourceNotFoundException;
import com.berkaykomur.ordermanagement.repository.CustomerAddressRepository;
import com.berkaykomur.ordermanagement.repository.CustomerRepository;
import com.berkaykomur.ordermanagement.repository.UserRepository;
import com.berkaykomur.ordermanagement.service.CustomerAddressService;
import com.berkaykomur.ordermanagement.utils.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class CustomerAddressServiceImplIntegrationTest {

    @Autowired
    private CustomerAddressService customerAddressService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    private Long savedCustomerId;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .username("username")
                .password("encodedPassword")
                .email("test@berkay.com")
                .role(Role.CUSTOMER)
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        Customer customer = Customer.builder()
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("+905050000000")
                .customerAddress(new ArrayList<>())
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .user(savedUser)
                .build();
        savedCustomerId = customerRepository.save(customer).getId();
    }

    @Test
    void createAddressSuccessfully() {
        AddressRequest request = TestDataUtil.setAddressRequest();
        AddressResponse response = customerAddressService.createAddress(savedCustomerId, request);
        assertNotNull(response.getId(),"Id could not find in database");
        assertThat(response)
                .usingRecursiveComparison()
                .ignoringFields("id","createdDate","isActive")
                .isEqualTo(request);
        Customer customer=customerRepository.findById(savedCustomerId).get();
        CustomerAddress savedAddress=customer.getCustomerAddress().get(0);
        assertThat(savedAddress)
                .usingRecursiveComparison()
                .ignoringFields("id", "customer", "createdDate", "lastModifiedDate","isActive") // Entity'de olup Request'te olmayanları görmezden gel
                .isEqualTo(request);


    }

    @Test
    void updateAddressSuccessfully() {
        AddressRequest initialRequest = TestDataUtil.setAddressRequest();
        AddressResponse created = customerAddressService.createAddress(savedCustomerId, initialRequest);

        AddressRequest updateRequest = new AddressRequest();
        updateRequest.setStreet("Papatya Sok.");
        AddressResponse updated = customerAddressService.updateAddress(savedCustomerId,created.getId(),updateRequest);
        assertEquals(updated.getStreet(),updateRequest.getStreet());

        assertThat(updated)
                .usingRecursiveComparison()
                .ignoringFields("id", "customer", "createdDate", "lastModifiedDate","isActive","street") // Entity'de olup Request'te olmayanları görmezden gel
                .isEqualTo(created);
    }

    @Test
    void deleteAddressSuccessfully() {
        AddressRequest request = TestDataUtil.setAddressRequest();
        AddressResponse address = customerAddressService.createAddress(savedCustomerId, request);
        customerAddressService.deleteAddress(savedCustomerId,address.getId());
        boolean exists = customerAddressRepository.findById(address.getId()).isPresent();
        assertFalse(exists, "The address could not be deleted");
    }
    @Test
    void updateAddress_ShouldThrowException_WhenAddressDoesNotBelongToCustomer() {
        User userB = User.builder()
                .username("userB").password("pass").email("b@test.com")
                .role(Role.CUSTOMER).isActive(true)
                .createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();
        userRepository.save(userB);
        Customer customerB = Customer.builder().firstName("B").lastName("B").phoneNumber("000").customerAddress(new ArrayList<>()).isActive(true).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).user(userB).build();
        Long customerBId = customerRepository.save(customerB).getId();

        AddressRequest addressBRequest = TestDataUtil.setAddressRequest();
        AddressResponse addressB = customerAddressService.createAddress(customerBId, addressBRequest);

        AddressRequest updateRequest = new AddressRequest();
        updateRequest.setStreet("Yeni Sokak");

        assertThrows(ResourceNotFoundException.class, () -> {
            customerAddressService.updateAddress(savedCustomerId, addressB.getId(), updateRequest);
        }, "Address of another customer cannot be updated!");
    }
    @Test
    void deleteAddress_ShouldThrowException_WhenAddressDoesNotBelongToCustomer() {
        User userB = User.builder()
                .username("userB").password("pass").email("b@test.com")
                .role(Role.CUSTOMER)
                .isActive(true).
                createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).build();
        userRepository.save(userB);
        Customer customerB = Customer.builder().firstName("B").lastName("B").phoneNumber("000").customerAddress(new ArrayList<>()).isActive(true).createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now()).user(userB).build();
        Long customerBId = customerRepository.save(customerB).getId();

        AddressRequest addressBRequest = TestDataUtil.setAddressRequest();
        AddressResponse addressB = customerAddressService.createAddress(customerBId, addressBRequest);

        assertThrows(ResourceNotFoundException.class, () -> {
            customerAddressService.deleteAddress(savedCustomerId, addressB.getId());
        }, "Address of another customer cannot be deleted!");
    }

}