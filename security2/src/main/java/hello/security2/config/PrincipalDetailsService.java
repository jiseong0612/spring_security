package hello.security2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hello.security2.model.User;
import hello.security2.repository.UserRepository;

//시큐리티 설정에서 loginProcessingUrl("/login");
//login 요청이 오면 자동으로 UserDetailsService타입의 IoC되어 있는 loadUserByUsername 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	//시큐리티 session(내부 Authentication(내부 UserDetails));
	//함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어 진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);	//PrincipalDetails implements UserDetails
		}
		return null;
	}

}
