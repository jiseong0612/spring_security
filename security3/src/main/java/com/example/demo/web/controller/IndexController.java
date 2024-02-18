package com.example.demo.web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.web.domain.User;
import com.example.demo.web.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserService service;

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
		type = "u".equals(type) ? "ROLE_USER" : "m".equals(type) ? "ROLE_MANAGER" : "ROLE_ADMIN";
		user.setRole(type);

		String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodePwd);
		
		//회원 타입, 일반 || 관리자
		if(user.getUserType().equals("memberType")) {
			service.memberSave(user);
		}else {
			service.adminSave(user);
		}
		
		return "redirect:/loginForm";
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
