package hello.security2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.security2.model.User;

//CRUD 함수를 JpaRepository가 들고 있음
//@Repository 라는 어노테이션 없어도 됨. 이유는 JpaRepository 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {
	//findBy 규칙
	//select * from user where username = 1?;
	public User findByUsername(String username);
}
