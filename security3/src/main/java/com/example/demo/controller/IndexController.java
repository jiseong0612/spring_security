package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	@GetMapping("/")
	public String index() {
		log.debug("test~~~~~");
		return "index";
	}

	@GetMapping("/login")
	@ResponseBody
	public String login() {
		return "login";
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

}
