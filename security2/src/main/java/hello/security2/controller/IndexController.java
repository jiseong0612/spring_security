package hello.security2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.security2.config.CustomBCryptPasswordEncoder;
import hello.security2.config.PrincipalDetails;
import hello.security2.model.User;
import hello.security2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	@Autowired
	private CustomBCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test/login")
	public @ResponseBody Object testLogin(@AuthenticationPrincipal PrincipalDetails details){
		log.info("details ==== {}", details);
		return details;
	}
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody Object testOauthLogin(@AuthenticationPrincipal OAuth2User oauth){
		log.info("oauth.getAttributes() ==== {}", oauth.getAttributes());
		return oauth.getAttributes();
	}
	
	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

	@GetMapping("/user")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		log.info("principalDetail = {}", principalDetails);
		
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
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
		log.info("user = {}", user);
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setRole("ROLE_USER");
		user.setPassword(encPassword);
		
		userRepository.save(user);
		return "redirect:/loginForm";
	}
	
	@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ROLE_ADMIN') and #username == authentication.name"), @Secured 보다 더 디테일하게 가능
	@GetMapping("/info")
	public String info() {
		return "info";
	}
}
