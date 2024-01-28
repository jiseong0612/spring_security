package com.pmg.security1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pmg.security1.config.PrincipalDetails;
import com.pmg.security1.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	@GetMapping("/list")
	public String main(Model model) {
		List<Map<String, String>> boardList = service.getBoardList();

		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	@GetMapping("/board")
	public String board(@RequestParam Map<String, String>inMap, Model model) {
		
		int bno = Integer.parseInt(inMap.get("bno"));
		
		Map<String, String> board = service.getBoard(bno);
		
		model.addAttribute("board", board);
		return "board/board";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, Object>inMap, Authentication auth) {
		PrincipalDetails user = (PrincipalDetails)auth.getPrincipal();
		inMap.put("id", user.getId());
		inMap.put("writer", user.getUsername());
		
		int result = service.boardInsert(inMap);
		
		return "redirect:/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/del")
	public ResponseEntity<Integer> insert(@RequestParam Map<String, String>inMap) {
		
		int result = service.deleBoard(Integer.parseInt(inMap.get("bno")));
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
