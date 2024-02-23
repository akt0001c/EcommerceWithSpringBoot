package com.ecs.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Orders")
public class Orders {
	
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="order_id")
  private Integer OrderId;
  
  @Column(name="estimate_delivery_date",nullable=false)
  private LocalDate estimateDeliveryDate;
  
  @JsonIgnore
  @ManyToOne(cascade=CascadeType.PERSIST)
  @JoinColumn(name="user_id")
  private Users user;
  
  @Column(name="status")
  @Enumerated(EnumType.STRING)
  private OrderStatus status;
  
  @Embedded
  private Address address;
  
  @Column(name="created_at")
  private LocalDateTime createdAt;
  
  
  @OneToMany(mappedBy="orders" ,cascade=CascadeType.ALL)
  private List<OrdersDetails> orderdetaillist = new ArrayList<>();
}
