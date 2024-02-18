package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.auth.PrincipalUserDetails;
import com.example.demo.web.domain.User;
import com.example.demo.web.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService service;
	
	@GetMapping("/info")
	@ResponseBody
	public String  info() {
		log.debug("errorrodjflkasfjdasklfdjsklfsajklfdsal");
		return "개인정보";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@PostMapping("/join")
	public String join(User user) {
		String type = user.getRole();
		type = "u".equals(type)? "ROLE_USER" : "m".equals(type)? "ROLE_MANAGER" : "ROLE_ADMIN";
		user.setRole(type);
		
		String encPwd = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPwd);
		service.userSave(user);
		return "redirect:/loginForm";
	}

	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalUserDetails userAccount) {
		System.out.println(userAccount);

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
