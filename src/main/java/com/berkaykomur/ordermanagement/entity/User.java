package com.berkaykomur.ordermanagement.entity;

import com.berkaykomur.ordermanagement.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name ="users")
@Getter
@Setter
@SQLDelete(sql = "UPDATE users set is_active=false where id =?")
@Where(clause = "is_active=true")
public class User extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;


}
