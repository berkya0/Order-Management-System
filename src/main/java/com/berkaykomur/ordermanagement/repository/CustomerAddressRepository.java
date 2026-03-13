package com.berkaykomur.ordermanagement.repository;

import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {

    Page<CustomerAddress> findAllByCustomerId(Long customerId, Pageable pageable);
    Optional<CustomerAddress> findByIdAndCustomerId(Long customerId, Long addressId);
}
