package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity{

    private int stockQuantity;
    private double price;
    private String imageUrl;
    @Column(nullable = false)
    private String productName;
    private String productDescription;
    @Column(nullable = false,unique = true)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;
}
