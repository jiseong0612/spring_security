package com.pmg.security1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity	//스프링 시큐리티 필터가 스프링 필터체인에 등록됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)	//secured 어노테이션 활성화
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		return http
//			.csrf().disable()
			.rememberMe()
			.rememberMeParameter("rememberMe")	//파라미터 명
			.tokenValiditySeconds(3600*24*365) 	//토큰 유지기간 1년
			.tokenRepository(persistentTokenRepository())
			.and()
			.authorizeRequests()
			.antMatchers("/user/**").authenticated()	//모든 권한 허용
			.antMatchers("/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()					//--로그인--
			.loginPage("/loginForm")		//로그인 URL
			.loginProcessingUrl("/login")	//login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행
			.successHandler(new CustomLoginSuccessHandler())	//로그인 성공시 
			.and()
			.logout()						//--로그아웃--
			.logoutUrl("/logout")			//로그아웃 URL
			.invalidateHttpSession(true)	//세션 무효화
			.deleteCookies("JSESSIONID")	//쿠키 삭제
			.logoutSuccessUrl("/")			//로그아웃 성공시 이동할 페이지(메인)
			.and().build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

}
