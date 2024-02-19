package com.example.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.example.jwt.config.jwt.JwtAuthenticationFilter;
import com.example.jwt.filter.MyFilter3;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
		http.csrf().disable();
		http.cors();
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.formLogin().disable().httpBasic().disable()
 			.addFilter(authenticationFilter);
 		
 		http.authorizeRequests()
 		.antMatchers("/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
 		.antMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
 		.antMatchers("/api/v1/admin/**").hasAnyRole("ADMIN")
 		.anyRequest().permitAll();
 		
 		return http.build();
 	}
}
