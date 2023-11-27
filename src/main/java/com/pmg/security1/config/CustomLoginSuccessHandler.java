package com.pmg.security1.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		PrincipalDetails user = (PrincipalDetails) authentication.getPrincipal();
		
		List<String> roleName = new ArrayList<String>();
		authentication.getAuthorities().forEach(authCollect->{
			roleName.add(authCollect.getAuthority());
		});
		String authStr = roleName.get(0);
		String role = user.getRole();
		
		if(role.contains("USER")) {
			response.sendRedirect("/user");
		}else if(role.contains("MANAGER")) {
			response.sendRedirect("/manager");
		}else if(role.contains("ADMIN")) {
			response.sendRedirect("/admin");
		}else {
			response.sendRedirect("/");
		}
	}

}
