package com.ecs.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class Users {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="user_id")
private Integer userId;

@Column(name="email" ,nullable=false)
@Email(message="user should have a valid email(abc@gmail.com)")
@NotEmpty(message="Email cannot be empty or null")
private String email;

@Column(name="password", nullable=false)
@NotEmpty(message="user should have password or cannot be null")
@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
private String password;

@NotEmpty(message="user should have valid first name")
@Column(name="fname" ,nullable=false)
private String fname;

@Column(name="lname")
private String lname;

@Embedded
@ElementCollection(fetch=FetchType.EAGER)
@JoinTable(name="users_address",joinColumns= @JoinColumn(name="user_id"))
private Set<Address> addresses = new HashSet<>();

@Column(name="location" ,nullable=false)
private String location;

@Column(name="role" ,nullable=false)
private String role;

@Enumerated(EnumType.STRING)
@Column(name="status")
private UserStatus status;

@Temporal(TemporalType.TIMESTAMP)
@Column(name="created_at")
private LocalDateTime createdAt;


@OneToOne( mappedBy= "user",cascade=CascadeType.ALL)
private Cart cart; 


@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<Orders> orders = new ArrayList<>();

}
