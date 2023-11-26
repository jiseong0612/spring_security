package com.pmg.security1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
