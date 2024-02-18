package com.example.demo.web.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.web.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	private final LoginService service;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@PostMapping("/login")
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
	public String register(@RequestParam Map<String, Object> inMap) {
		log.debug("inMap = {}", inMap);

		String encPassword = bCryptPasswordEncoder.encode(inMap.get("password").toString());
		inMap.put("password", encPassword);

		Integer result = service.save(inMap);

		return "redirect:/loginForm";
	}

}
