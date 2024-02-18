package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring()
//				.antMatchers("/resources/**", "/favicon.ico");
//	}
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.antMatchers("/public/**").permitAll()	
				//.antMatchers("/api/**").permitAll()	
//				.antMatchers("/api/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/user/**").authenticated()	//로그인된 모든 유저면 해당 루트 접근 가능	
				.antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		http.csrf().disable();
//		http
//		.csrf().disable()
//		.httpBasic()
//		.formLogin()
//			.loginPage("loginForm")
//			.loginProcessingUrl("/login")
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
//				.build();
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password")
//				.roles("ADMIN", "USER").build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // memory 방식으로 사용자 build
//
//        String password = passwordEncoder().encode("1111");
//
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER", "USER");
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN", "MANAGER", "USER");
//    }

}
