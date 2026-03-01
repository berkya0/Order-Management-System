package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "customer_address")
@Getter
@Setter
@SQLDelete(sql = "UPDATE customer_address set is_active=false where id =?")
@Where(clause = "is_active=true")
public class CustomerAddress extends  BaseAddress{

    @ManyToOne
    private Customer customer;


}
