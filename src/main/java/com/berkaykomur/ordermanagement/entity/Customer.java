package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@SQLDelete(sql = "UPDATE customer set is_active=false where id =?")
@Where(clause = "is_active=true")
public class Customer extends Person{

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddress;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
