package com.pmg.security1.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pmg.security1.domain.User;
import com.pmg.security1.service.UserService;

//시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행! (규칙)
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserService service;
	
	//시큐리티 session = Authentication = UserDetails
	//결과 : 시큐리티 session(내부 Authentication(내부 UserDetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = new User();
		Map<String, String> userMap = service.findByUsername(username);
		int userid = Integer.parseInt(String.valueOf(userMap.get("id")));
		user.setId(userid);
		user.setUsername(userMap.get("username"));
		user.setPassword(userMap.get("password"));
		user.setEmail(userMap.get("email"));
		user.setRole(userMap.get("role"));
		
		if(user != null) {
			return new PrincipalDetails(user);
		}
		return null;
	}

}
