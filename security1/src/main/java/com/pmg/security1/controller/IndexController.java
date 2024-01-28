package com.pmg.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pmg.security1.config.PrincipalDetails;
import com.pmg.security1.domain.User;
import com.pmg.security1.service.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/")
	public String index(Authentication auth, Model mdoel) {
		if(auth != null) {
			PrincipalDetails  user = (PrincipalDetails)auth.getPrincipal();
			mdoel.addAttribute("userId", user.getUsername());
		}
		
		return "index";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "info";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}

	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user <br> <a href='/list'>post List</a>";
	}

	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager <br> <a href='/list'>post List</a>";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin <br> <a href='/list'>post List</a>";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		String type = user.getRole();
		type = "u".equals(type)? "ROLE_USER" : "m".equals(type)? "ROLE_MANAGER" : "ROLE_ADMIN";
		
		user.setRole(type);
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		service.save(user);
		return "redirect:/loginForm";
	}
}
