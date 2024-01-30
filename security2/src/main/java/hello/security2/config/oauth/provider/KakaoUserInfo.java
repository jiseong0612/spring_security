package hello.security2.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{

	private Map<String, Object> getAttributes;
	
	public KakaoUserInfo(Map<String, Object> getAttributes) {
		this.getAttributes = getAttributes;
	}
	
	@Override
	public String getProviderId() {
		return (String)getAttributes.get("id");
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getEmail() {
		return (String)getAttributes.get("email");
	}

	@Override
	public String getName() {
		return (String)getAttributes.get("name");
	}

}
