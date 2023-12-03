package com.pmg.security1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmg.security1.service.ReplyService;

@Controller
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@GetMapping("/reply")
	@ResponseBody
	public List<Map<String, String>> reply(@RequestParam Map<String, String>inMap){
		
		Map<String, String> dataMap = new HashMap<>();
		
		List<Map<String, String>> replyList = service.getReplyList(dataMap);
		
		return replyList;
	}
}
