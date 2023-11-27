package com.pmg.security1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping("/board")
	public String main(Model model) {
		List<Map<String, String>> boardList = service.getBoardList();

		model.addAttribute("boardList", boardList);
		return "board/board";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, Object>boardMap, Authentication auth) {
		PrincipalDetails user = (PrincipalDetails)auth.getPrincipal();
		boardMap.put("id", user.getId());
		
		int result = service.boardInsert(boardMap);
		
		return "redirect:/board";
	}
}
