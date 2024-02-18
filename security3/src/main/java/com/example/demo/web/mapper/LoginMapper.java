package com.example.demo.web.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	public Integer save(Map<String, Object>inMap);
	
	public String findByUsername(String id);
}
