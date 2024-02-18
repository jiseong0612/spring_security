package com.example.demo.web.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.web.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final LoginMapper mapper;

	public Integer save(Map<String, Object> inMap) {
		return mapper.save(inMap);
	}

	public String findByUsername(String id) {
		return mapper.findByUsername(id);
	}
}