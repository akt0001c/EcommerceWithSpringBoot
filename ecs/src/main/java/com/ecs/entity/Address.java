package com.ecs.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

 @Enumerated(EnumType.STRING)
 private AddressType address_type;
 
 @Column(name="adh_name",nullable=false)
 @NotEmpty(message="Address holder should have a valid name")
 private String adh_name;
 
 @Column(name="hno_street_area" ,nullable=false)
 @NotEmpty(message="house no or street number or area cannot be empty or null")
 private String hno_street_area ;
 
 @Column(name="city",nullable=false)
 @NotEmpty(message="address should have a valid city")
 private String city;
 
 @Column(name="phoneno",nullable=false)
 @NotEmpty(message="Address should have a valid phoneno")
 private String phoneno;
 
 @Column(name="state",nullable=false)
 @NotEmpty(message="Address should have a valid state name")
 private String state;
 
 @Column(name="pincode",nullable=false)
 @NotEmpty(message="Address should have a valid pincode")
 private String pincode;
 
 @Column(name="added_at" ,nullable=false)
 @Temporal(TemporalType.TIMESTAMP)
 private LocalDateTime added_at;
	

}
