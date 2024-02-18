package com.example.demo.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.web.domain.User;

@Mapper
public interface UserMapper {

	public User findByUsername(String username);

	public User findByAdminname(String username);

	public void userSave(User user);
}
