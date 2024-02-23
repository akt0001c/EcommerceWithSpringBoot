package com.ecs.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart_details")
public class CartDetails {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="cart_detail_id")
 private Integer cartDetailId;
 
 @ManyToOne(cascade=CascadeType.PERSIST)
 @JoinColumn(name="product_id")
 private Products product;
 
 @JsonIgnore
 @ManyToOne(cascade=CascadeType.PERSIST)
 @JoinColumn(name="cart_id")
 private Cart cart;
 
 @Min(value=1 ,message="Product quantity can't be less than 1")
 @Column(name="quantity")
 private Integer quantity;
 
 @Column(name="added_at")
 @Temporal(TemporalType.TIMESTAMP)
 private LocalDateTime addedAt;
 
 
}
