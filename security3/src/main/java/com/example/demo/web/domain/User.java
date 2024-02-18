package com.example.demo.web.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String provider;
	private String providerId;
	private String createdate;
	private String userType;
	
	
	
	@Builder
	public User(String username, String password, String email, String role, String provider, String providerId,
			String createdate, String userType) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
		this.createdate = createdate;
		this.userType = userType;
	}
}

