package com.example.jwt.config.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final AuthenticationManager authenticationManager;
	
	// /login 요청을 하면 로그인을 위해 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("진짜 로그이 요청되면 들어옴? JwtAuthenticationFilter");
		System.out.println("진짜 로그이 요청되면 들어옴? JwtAuthenticationFilter");
		System.out.println("진짜 로그이 요청되면 들어옴? JwtAuthenticationFilter");
		System.out.println("진짜 로그이 요청되면 들어옴? JwtAuthenticationFilter");
		return super.attemptAuthentication(request, response);
	} 
}
