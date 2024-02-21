/************************************************************************
* 업무명		: 
* 서브 업무명	: 
* 설명		: 
*
* 작성자		: 
* 작성일		: 
* 수정자		: 
* 수정일		: 
************************************************************************/
package com.example.jwt.config.jwt;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalUserDetails;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository	 = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("인증이나 권한이 필요한 주소 요청이 됨");
		System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader : " + jwtHeader);
		
		//비정상 케이스: 넘겨버림
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		//정상
		String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		
		String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString();
		
		//jwt 토큰 서명을 통해서 서명이 정사이면 Authentication 객체를 만들어 준다.
		if(username != null) {
			User user = userRepository.findByUsername(username);
			
			PrincipalUserDetails principalUserDetails = new PrincipalUserDetails(user);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalUserDetails, null, principalUserDetails.getAuthorities());
			
			//강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
			
		}
	}

}
