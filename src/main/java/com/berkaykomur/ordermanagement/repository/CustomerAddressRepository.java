package com.berkaykomur.ordermanagement.repository;

import com.berkaykomur.ordermanagement.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Long> {

}
