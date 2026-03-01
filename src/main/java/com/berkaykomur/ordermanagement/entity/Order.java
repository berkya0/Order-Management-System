package com.berkaykomur.ordermanagement.entity;

import com.berkaykomur.ordermanagement.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@SQLDelete(sql = "UPDATE orders set is_active=false where id =?")
@Where(clause = "is_active=true")
public class Order extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    private String notes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_address_id")
    private OrderAddress orderAddress;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<Payment>  payments;
}
