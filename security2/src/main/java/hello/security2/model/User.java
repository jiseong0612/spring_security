package hello.security2.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String username;
	private String password;
	private String email;
	private String role; //ROLE_USER, ROLE_ADMIN
	@CreationTimestamp
	private Timestamp createDate;
	@CreationTimestamp
	private Timestamp LastLoginDate;
	private String provider;
	private String providerId;
	
	
	@Builder
	public User(String username, String password, String email, String role, Timestamp createDate,
			Timestamp lastLoginDate, String provider, String providerId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.createDate = createDate;
		LastLoginDate = lastLoginDate;
		this.provider = provider;
		this.providerId = providerId;
	}
}
