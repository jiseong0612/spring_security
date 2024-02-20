package com.example.jwt.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalUserDetails;
import com.example.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager; // /login 요청을 하면 로그인을 위해 실행되는 함수

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			//PrincipalDetailsService의 loadUserByUsername() 함수가 실행됨
			//DB에 있는 username 과 password가 일치한다.
			Authentication authentication = 
					authenticationManager.authenticate(authenticationToken);
			
			// => 로그인이 되었다! //authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return해주면 됨
			PrincipalUserDetails principalUserDetails = (PrincipalUserDetails) authentication.getPrincipal();
			System.out.println("로그인 완료됨? :" + principalUserDetails.getUser().getUsername());	//로그인 정상적으로 되었다는 뜻
			
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행!
	//JWT 토큰을 만들어서 request한 사용자에게 토큰을 response해주면 됨.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication 실행되 : 인증이 완료되었다는 뜻임");
		
		PrincipalUserDetails principalDetailis = (PrincipalUserDetails)authResult.getPrincipal();
		
		String jwtToken = JWT.create()
				.withSubject("cos 토큰")	//토큰 이름
				.withExpiresAt(new Date(System.currentTimeMillis()+ (60000 * 10))) //10분.
				.withClaim("id", principalDetailis.getUser().getId())
				.withClaim("username", principalDetailis.getUser().getUsername())
				.sign(Algorithm.HMAC512("cos"));	//토큰의 고유한 값
		
		response.addHeader("Authorization", "Bearer "+jwtToken);
		
		System.out.println("토큰 발급 완료");
		
//		String jwtToken = JWT.create()
//				.withSubject(principalDetailis.getUsername())
//				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
//				.withClaim("id", principalDetailis.getUser().getId())
//				.withClaim("username", principalDetailis.getUser().getUsername())
//				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
//		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
	}
}
