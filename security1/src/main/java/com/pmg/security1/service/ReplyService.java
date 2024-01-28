package com.pmg.security1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmg.security1.mapper.ReplyMapper;

@Service
public class ReplyService {

	@Autowired
	private ReplyMapper mapper;

	public List<Map<String, String>> getReplyList(Map<String, String> dataMap) {
		return mapper.getReplyList(dataMap);
	}
}
