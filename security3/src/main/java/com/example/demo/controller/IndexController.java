package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/api")
public class IndexController {

	@GetMapping("/")
	@ResponseBody
	public String index() {
		log.debug("test~~~~~");

		return "index";
	}

	@GetMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestParam Map<String, Object> inMap) {
		log.debug("inMap = {}", inMap);
		
		return new ResponseEntity<Map<String, Object>>(inMap, HttpStatus.OK);
	}

	@GetMapping("/logout")
	@ResponseBody
	public String logout() {
		return "/logout";
	}

	@GetMapping("/user")
	@ResponseBody
	public String user() {
		return "user";
	}

	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "manager";
	}

	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin";
	}

	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getMethodName(@RequestBody Map<String, Object> inMap) {
		log.debug("inMap = {}", inMap);

		return new ResponseEntity<Map<String, Object>>(inMap, HttpStatus.OK);
	}

}
