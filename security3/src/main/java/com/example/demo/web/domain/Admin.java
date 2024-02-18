package com.example.demo.web.domain;

import lombok.Data;

@Data
public class Admin {
	private int id;
	private String username;
	private String email;
	private String role;
	private String provider;
	private String providerId;
	private String createdate;
}
