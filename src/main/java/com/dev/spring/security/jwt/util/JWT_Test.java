package com.dev.spring.security.jwt.util;


import java.util.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWT_Test {
	
    private static String secret_key = "J@!gt*K";
    
	public static void main(String[] args) {
		
		// code to test generated Token
		String token= JWTUtil.generateToken("Token1", secret_key);
		System.out.println("------------------------TOKEN----------------------------------------------------");
		System.out.println(token);
		System.out.println();
		System.out.println("------------------------CLAIMS----------------------------------------------------");
		
		//code to test parsed token : Claims
		
		Claims claims= Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(secret_key.getBytes()))
				.parseClaimsJws(token)
				.getBody();
		
		System.out.println("Token ID: "+claims.getId());
		System.out.println("Token Subject: "+claims.getSubject());
		System.out.println("Token Issuer: "+claims.getIssuer());
		System.out.println("Token Issue Date: "+claims.getIssuedAt());
		System.out.println("Token Expiration Date: "+claims.getExpiration());
		System.out.println("Token Audience: "+claims.getAudience());
	}
}