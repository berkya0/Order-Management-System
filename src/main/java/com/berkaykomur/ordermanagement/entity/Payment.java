package com.berkaykomur.ordermanagement.entity;

import com.berkaykomur.ordermanagement.enums.PaymentStatus;
import com.berkaykomur.ordermanagement.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    private String notes;

    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;


}
