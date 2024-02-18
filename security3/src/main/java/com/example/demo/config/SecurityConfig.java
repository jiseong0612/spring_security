package com.example.demo.config;

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
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
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
			.loginPage("/loginForm")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/");
	 		
 		http.rememberMe()
			.rememberMeParameter("rememberMe")	//파라미터 명
			.tokenValiditySeconds(3600*24*365) 	//토큰 유지기간 1년
			.tokenRepository(persistentTokenRepository());
 			
		return http.build();
	}
}
