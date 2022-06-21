package com.dev.spring.security.jwt.entity;

import lombok.Data;

@Data
public class UserRequest {

	private String userName;	
	private String password;
}