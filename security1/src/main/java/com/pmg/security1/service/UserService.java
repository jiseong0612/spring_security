package com.pmg.security1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmg.security1.domain.User;
import com.pmg.security1.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;
	
	public int save(User user) {
		return mapper.save(user);
	}
	
	public Map<String, String> findByUsername(String username) {
		return mapper.findByUsername(username);
	}
}
