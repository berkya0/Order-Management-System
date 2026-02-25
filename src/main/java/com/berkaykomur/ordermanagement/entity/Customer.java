package com.berkaykomur.ordermanagement.entity;

import com.berkaykomur.ordermanagement.enums.CustomerStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false ,unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerStatus customerStatus;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<CustomerAddress> customerAddress;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
