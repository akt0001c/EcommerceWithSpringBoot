package com.ecs.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Integer cartId;
	
	@JsonIgnore
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private Users user;
	
	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy="cart",cascade= CascadeType.ALL)
	private List<CartDetails> cartdetailslist = new ArrayList<>();
	
}
