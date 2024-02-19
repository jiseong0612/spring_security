package com.example.jwt.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

public class PrincipalUsersService implements UserDetailsService{
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		
		if(user != null) {
			return new PrincipalUserDetails(user);
		}
		return null;
	}

}
