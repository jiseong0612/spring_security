package com.example.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {
	@GetMapping("/home")
	public String home() {
		System.out.println("api home");
		return "<h1>home</h1>";
	}
	
	@PostMapping("/login")
	public String login() {
		System.out.println("api login");
		return "login";
	}
}
