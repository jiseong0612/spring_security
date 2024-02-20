package com.example.jwt.config.jwt;

//@Data
//@Component
//@ConfigurationProperties("jwt")	application.yml에 설정된 값을 읽는건데 일단 끄고 하드코딩ㄱㄱ
//public class  JwtProperties {
public interface  JwtProperties {
	String SECRET = "조익현"; // 우리 서버만 알고 있는 비밀값
	int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
