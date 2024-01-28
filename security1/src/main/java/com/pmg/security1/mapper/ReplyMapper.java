package com.pmg.security1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
	List<Map<String, String>> getReplyList(Map<String, String> dataMap);
}
