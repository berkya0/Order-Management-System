package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseAddress extends BaseEntity{
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
