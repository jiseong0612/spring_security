package com.pmg.security1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmg.security1.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper mapper;
	
	public List<Map<String, String>> getBoardList(){
		return mapper.getBoardList();
	}
	
	public int boardInsert(Map<String, Object>boardMap) {
		return mapper.boardInsert(boardMap);
	}
	
	public int deleBoard(int bno) {
		return mapper.deleBoard(bno);
	}
}
