package hello.security2.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import hello.security2.config.CustomBCryptPasswordEncoder;
import hello.security2.config.PrincipalDetails;
import hello.security2.model.User;
import hello.security2.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private CustomBCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	//구글로 받은 후처리 함수
	//함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어 진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		log.info("getClientRegistration  = {}", userRequest.getClientRegistration());	//어떤 Oauth로 로그인 했는지 확인 가능
		log.info("super.loadUser(userRequest)  = {}", oAuth2User);
		
		
		String provider = userRequest.getClientRegistration().getRegistrationId();	//google
		String providerId = oAuth2User.getAttribute("sub");
		String username = provider+"_"+providerId;	//ex ) google_12312321312
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oAuth2User.getAttribute("email");
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByUsername(username);
		//회원가입 강제!!
		if(userEntity == null) {
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId).build();
			userRepository.save(userEntity);
		}
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}

}
