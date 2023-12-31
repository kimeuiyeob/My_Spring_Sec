package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO 역할을 한다.
//자동으로 Bean으로 등록된다.
//@Repository <= 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

	// JPA Naming 전략
	// select * from user where username = ? and password = ?;
//	public User findByusernameAndPassword(String username, String password);

	// queryDSL 위랑 동일한 값을 리턴한다.
//	@Query(value = "select * from user where username = ? and password = ?", nativeQuery = true)
//	public User login(String username, String password);

	// ====================================================================
	// JPA Naming 전략
	public Optional<User> findByusername(String username);

}
