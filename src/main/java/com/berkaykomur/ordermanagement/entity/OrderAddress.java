package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_address")
@Getter
@Setter
public class OrderAddress extends BaseEntity{
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
}
