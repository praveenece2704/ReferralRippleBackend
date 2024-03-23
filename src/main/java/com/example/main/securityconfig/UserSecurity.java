package com.example.main.securityconfig;

import java.util.Base64;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSecurity {

	public String passwordEncoder(String password) {
		System.out.println(new String(Base64.getEncoder().encode(password.getBytes())));
		return new String(Base64.getEncoder().encode(password.getBytes()));
	}
	
	public String passwordDecoder(byte[] password) {
		System.out.println(new String(Base64.getDecoder().decode(password)));
		return new String(Base64.getDecoder().decode(password));
	}
    
}
