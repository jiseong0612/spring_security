package hello.security2.config.oauth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import hello.security2.config.CustomBCryptPasswordEncoder;
import hello.security2.config.PrincipalDetails;
import hello.security2.config.oauth.provider.GoogleUserInfo;
import hello.security2.config.oauth.provider.KakaoUserInfo;
import hello.security2.config.oauth.provider.NaverUserInfo;
import hello.security2.config.oauth.provider.OAuth2UserInfo;
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
		log.info("getClientRegistration  = {}", userRequest.getClientRegistration());	//어떤 Oauth로 로그인 했는지 확인 가능
		log.info("getAccessToken  = {}", userRequest.getAccessToken().getTokenValue());
		
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		//구글 로그인 버튼 클릭 -> 로그인창 -> 로그인완료 -> 코드리턴(Oauth-client라이브러리) -> AccessToken 요청
		//userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필 받아준다.
		log.info("getAttributes = {}",oAuth2User.getAttributes());
		
		//회원가입 강제로 진행!!
		OAuth2UserInfo oauth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			log.info("구글 로그인 요청");
			oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			log.info("네이버 로그인 요청");
			oauth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			log.info("카카오 로그인 요청");
			Map<String, Object> kakaoAccountMap = (Map<String, Object>)oAuth2User.getAttributes().get("kakao_account");
			
			Map<String, Object> attrMap = new HashMap<String, Object>();
			attrMap.put("id", oAuth2User.getAttributes().get("id").toString());
			attrMap.put("email", kakaoAccountMap.get("email"));
			
 			oauth2UserInfo = new KakaoUserInfo(attrMap);
			
		}else {
			log.info("우리는 구글과 페이스북, 네이버만 지원해요 ㅎㅎㅎ");
		}
		String provider = oauth2UserInfo.getProvider();	//google, facebook, naver
		String providerId = oauth2UserInfo.getProviderId();
		String username = provider+"_"+providerId;	//ex ) google_12312321312
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oauth2UserInfo.getEmail();
		String role = "ROLE_USER";
		
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			log.info("OAuth 로그인이 최초 입니다.");
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId).build();
			userRepository.save(userEntity);
		}else {
			log.info("로그인을 이미 한 적이 있습니다. 당신은 자동 회원가입이 되어 있습니다.");
		}
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}

}
