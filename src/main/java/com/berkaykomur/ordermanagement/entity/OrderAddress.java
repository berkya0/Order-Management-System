package com.berkaykomur.ordermanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "order_address")
@Getter
@Setter
@SQLDelete(sql = "UPDATE order_address set is_active=false where id =?")
@Where(clause = "is_active=true")
public class OrderAddress extends BaseAddress{


}
