package com.example.demo.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.config.CustomBCryptPasswordEncoder;
import com.example.demo.config.auth.PrincipalUserDetails;
import com.example.demo.web.domain.User;
import com.example.demo.web.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserService service;
	
	@Autowired
	private CustomBCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		//회원가입 강제 진행
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String provider = "google";
		String providerId = oauth2User.getAttribute("sub");
		String username = provider + "_" + providerId;
		String password = bCryptPasswordEncoder.encode("commonOauthPassword");
		String email = oauth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		User user = service.findbyusername(username);
		
		if(user == null) {
			user = user.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.build();
			
			service.memberSave(user);
		}
		
		return new PrincipalUserDetails(user, oauth2User.getAttributes());
	}
}
