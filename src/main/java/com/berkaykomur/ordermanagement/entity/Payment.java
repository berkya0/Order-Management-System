package com.berkaykomur.ordermanagement.entity;

import com.berkaykomur.ordermanagement.enums.PaymentStatus;
import com.berkaykomur.ordermanagement.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "payment")
@Getter
@Setter
@SQLDelete(sql = "UPDATE payment set is_active=false where id =?")
@Where(clause = "is_active=true")
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
