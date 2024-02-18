package com.example.demo.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.web.domain.User;
import com.example.demo.web.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;

	public User findbyusername(String id) {
		return mapper.findByUsername(id);
	}

	public User findByAdminname(String id) {
		return mapper.findByAdminname(id);
	}

	public void memberSave(User user) {
		mapper.memberSave(user);
	}
	public void adminSave(User user) {
		mapper.adminSave(user);
	}
}