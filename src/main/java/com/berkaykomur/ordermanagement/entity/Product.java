package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@SQLDelete(sql = "UPDATE product set is_active=false where id =?")
@Where(clause = "is_active=true")
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

    @ManyToOne
    @JoinColumn(name = "seller_id",nullable = false)
    private Seller seller;
}
