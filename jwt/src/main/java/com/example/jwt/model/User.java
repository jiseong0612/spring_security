package com.example.jwt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String password;
	private String roles;	//
	private String email;
	
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}else {
			return new ArrayList<String>();
		}
	}
}
