package com.example.demo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.web.domain.User;
import com.example.demo.web.service.UserService;

@Service
public class PrincipalUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = service.findbyusername(username);
		if(user != null) {
			return new PrincipalUserDetails(user);
		}
		
		User admin = service.findByAdminname(username);
		if(admin != null) {
			return new PrincipalUserDetails(admin);
		}
		
		return null;
	}

}
