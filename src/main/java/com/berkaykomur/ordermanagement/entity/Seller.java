package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "seller")
@Getter
@Setter
@SQLDelete(sql = "UPDATE seller set is_active=false where id =?")
@Where(clause = "is_active=true")
public class Seller extends Person {

    private String brandName;

    @OneToMany(mappedBy = "seller")
    private List<Product> sellingProducts;



}
