package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "category")
@Getter
@Setter
@SQLDelete(sql = "UPDATE category set is_active=false where id =?")
@Where(clause = "is_active=true")

public class Category extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String categoryName;
}
