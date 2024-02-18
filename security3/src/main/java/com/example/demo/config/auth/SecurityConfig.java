package com.example.demo.config.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
 		http.authorizeRequests()
	 		.antMatchers("/api/**").authenticated()
 			.antMatchers("/user/**").authenticated()
 			.antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
 			.antMatchers("/admin/**").hasRole("ADMIN")
 			.anyRequest().permitAll();
 		
 		http.formLogin()
 			.loginProcessingUrl("/login")
 			.successHandler(customAuthenticationSuccessHandler)
 			.failureHandler(customAuthenticationFailureHandler)
 			;
// 			.loginPage("/loginForm")
//			.successHandler((request, response, authentication) -> {
//			    response.sendRedirect("/");
//			})
//			.failureHandler((request, response, exception) -> {
//			    response.sendRedirect("/login");
//			// 참고로 스프링 시큐리티가 제공하는 로그인 페이지 주소는
//			// "/login"이다.
//			})
 		
 		http.logout()
 			.logoutUrl("/logout")
 			.deleteCookies("rememberMe", "JSESSIONID")
 			.invalidateHttpSession(true);
// 			.logoutSuccessHandler((request, response, authentication) -> {
//                 response.sendRedirect("/login");
//             });
 			
 		http.rememberMe()
			.rememberMeParameter("rememberMe")	//파라미터 명
			.tokenValiditySeconds(3600*24*365) 	//토큰 유지기간 1년
			.tokenRepository(persistentTokenRepository());
 		
 		return http.build();
 	}

}
