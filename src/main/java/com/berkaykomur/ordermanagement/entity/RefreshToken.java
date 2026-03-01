package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@SQLDelete(sql = "UPDATE refresh_token set is_active=false where id =?")
@Where(clause = "is_active=true")
public class RefreshToken extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String refreshToken;

    private boolean revoked=false;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
