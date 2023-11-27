package com.pmg.security1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

	public List<Map<String, String>> getBoardList();
}
