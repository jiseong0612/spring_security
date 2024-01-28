package hello.security2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import hello.security2.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)	//secured 어노테이션 활성화
public class SecurityConfig {
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	//해당 메서드의 리턴되는 오브젝트를  IoC로 등록해 준다.
//	@Bean
//	public BCryptPasswordEncoder encodePwd() {
//		return new BCryptPasswordEncoder();
//	}
	
	@Bean
	@Order(1)                                                        
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeHttpRequests(authorize -> authorize
					.antMatchers("/user/**").authenticated()	//로그인된 모든 유저면 해당 루트 접근 가능	
					.antMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().permitAll());	//로그인 사용자, 익명 사용자 모두 접근 가능하려면 permitAll().
		
		http.formLogin()
		.loginPage("/loginForm")
		.loginProcessingUrl("/login")	//login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행
//		.successHandler(authenticationSuccessHandler())
		.defaultSuccessUrl("/"); //로그인 성공 후 redirect
		
		http.oauth2Login().loginPage("/loginForm")
			.userInfoEndpoint()
			.userService(principalOauth2UserService);	//구글 로그인 후 후처리 필요, 코드x(엑세서 토큰 + 사용자 프로필정보 ) Oauth 라이브러리의 장점!
		
		return http.build();
	}
}