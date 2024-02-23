package com.ecs.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Products")
public class Products {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="product_id")
  private Integer productId;
  
  @Column(name="title",nullable=false)
  @NotEmpty(message="Product should have a valid title")
  private String title;
  
  @DecimalMin(value="0.0" ,message="Product should have a valid price which should be greater than or equal to 0.0")
  @Column(name="price",nullable=false)
  @NotNull(message="Product price cannot be null")
  private double price;
  
  @Column(name="description")
  private String description;
  
  @NotNull(message="Product quantity cannot be null")
  @Min(value=1,message="Product should have at least 1 quantity")
  private Integer quantity;
  
  @Column(name="status")
  @Enumerated(EnumType.STRING)
  private ProductStatus status;
  
  @Column(name="added_at")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime addedAt;
  
  @JsonIgnore
  @OneToMany(mappedBy="product", cascade=CascadeType.ALL)
  private List<CartDetails> cartDetailslist = new ArrayList<>();
}
