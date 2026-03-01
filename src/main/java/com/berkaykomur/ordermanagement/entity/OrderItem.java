package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@SQLDelete(sql = "UPDATE order_item set is_active=false where id =?")
@Where(clause = "is_active=true")
public class OrderItem extends BaseEntity {

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    private Double unitPrice;
    private Double totalPrice;

}
