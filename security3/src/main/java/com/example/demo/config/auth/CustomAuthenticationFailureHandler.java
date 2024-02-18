package com.example.demo.config.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMessage = null;

		if (exception instanceof BadCredentialsException) {
			errorMessage = "아이디와 비밀번호를 확인해주세요.";
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "내부 시스템 문제로 로그인할 수 없습니다. 관리자에게 문의하세요.";
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 계정입니다.";
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			errorMessage = "알 수없는 오류입니다.";
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		// Map을 JSON으로 변환하여 응답합니다.
		errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(errorMessage));

	}

}
