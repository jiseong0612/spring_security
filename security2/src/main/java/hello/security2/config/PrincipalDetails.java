package hello.security2.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import hello.security2.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails, OAuth2User {
	/*"컴포지션"은 객체 지향 프로그래밍에서 두 클래스 간의 관계를 나타내는 용어 중 하나입니다. 컴포지션은 한 클래스가 다른 클래스의 객체를 포함하는 방식으로 관계를 형성하는 것을 말합니다.
	스프링 시큐리티의 implements UserDetails private User user;에서 UserDetails 인터페이스를 구현하는 클래스가 User 클래스의 인스턴스를 포함하고 있다는 것을 나타냅니다. 이러한 관계를 컴포지션 관계라고 합니다.
	implements UserDetails는 해당 클래스가 스프링 시큐리티의 UserDetails 인터페이스를 구현하고 있음을 나타냅니다. 이 인터페이스는 스프링 시큐리티에서 사용자 정보를 제공하는 데 필요한 메서드를 정의하고 있습니다. 그리고 private User user; 부분은 해당 클래스가 User 클래스의 인스턴스를 포함하고 있다는 것을 의미합니다.
	이러한 구조는 코드 재사용성과 모듈성을 증가시키는 데 도움이 되며, 객체 간의 강한 결합을 피할 수 있습니다. 예를 들어, 사용자 정보와 관련된 기능은 User 클래스에 구현되어 있고, 보안 및 인증과 관련된 기능은 UserDetails 인터페이스를 구현한 클래스에 구현되어 있습니다.
	*/
	private User user;	//컴포지션
	private Map<String, Object>attributes;

	//일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//Oauth 로그인
	public PrincipalDetails(User user, Map<String, Object>attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		//구글  Oauth 로그인 시 user id pk값을 가져옴
		return attributes.get("sub").toString();
	}
}
