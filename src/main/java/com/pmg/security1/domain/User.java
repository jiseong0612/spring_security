package com.pmg.security1.domain;

import lombok.Data;

@Data
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;//ROLE_USER, ROLE_ADMIN
	private String createDate;
}
