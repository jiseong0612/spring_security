package com.pmg.security1.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.pmg.security1.domain.User;

@Mapper
public interface UserMapper {
	int save(User user);
	
	Map<String, String> findByUsername(String username);
}
