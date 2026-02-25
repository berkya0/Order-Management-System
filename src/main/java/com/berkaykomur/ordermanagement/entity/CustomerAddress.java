package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_address")
@Getter
@Setter
public class CustomerAddress extends  BaseEntity{
    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String buildingNo;

    @Column(nullable = false)
    private String apartmentNo;

    @Column(nullable = false)
    private String fullAddress;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Customer customer;

    private Boolean isActive;

}
