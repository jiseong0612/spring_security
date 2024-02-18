package com.example.demo.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.CustomBCryptPasswordEncoder;
import com.example.demo.config.auth.PrincipalUserDetails;
import com.example.demo.web.domain.User;
import com.example.demo.web.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IndexController {

	private final CustomBCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserService service;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/test/login")
	@ResponseBody
	public String testLogin(@AuthenticationPrincipal PrincipalUserDetails details) {
		System.out.println(details.getUser());
		return "시큐리티 세션 정보 확인"+ details.getUser();
	}
	@GetMapping("/test/oauthLogin")
	@ResponseBody
	public String oauthLogin(Authentication authentication) {
		OAuth2User oAuth2User =  (OAuth2User) authentication.getPrincipal();
		System.out.println(oAuth2User.getAttributes());
		return "시큐리티 세션 정보 확인"+ oAuth2User;
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
