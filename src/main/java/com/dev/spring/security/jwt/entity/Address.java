package com.dev.spring.security.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="add_table")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {
	
	@Id
	private long aid;
	private String street;
	private String city;
	private String state;
	private int pincode;
  }