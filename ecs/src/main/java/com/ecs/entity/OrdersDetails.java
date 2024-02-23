package com.ecs.entity;

import java.time.LocalDateTime;

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
@Table(name="Orders_details")
public class OrdersDetails {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="order_detail_id")
   private Integer OrdersDetailsId;
   
   @ManyToOne(cascade=CascadeType.PERSIST)
   @JoinColumn(name="order_id")
   private Orders orders;
   
   @ManyToOne(cascade=CascadeType.PERSIST)
   @JoinColumn(name="product_id")
   private Products product;
   
   @Min(value=1,message="Quantity should be at least 1")
   @Column(name="quantity")
   private Integer quantity;
   
   @Column(name="added_at")
   @Temporal(TemporalType.TIMESTAMP)
   private LocalDateTime addedAt;
}
