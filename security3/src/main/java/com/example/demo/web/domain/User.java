package com.example.demo.web.domain;

import lombok.Data;

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
}
