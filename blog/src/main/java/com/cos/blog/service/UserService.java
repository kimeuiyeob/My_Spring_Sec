package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 한다. IOC를 해준다는 뜻이다. -> 메모리에 띄어준다는것!!
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encode;

	@Transactional(readOnly = true)
	public User findByName(String username) {
		User user = userRepository.findByusername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}

	@Transactional // 전체를 @Transactional묶고 전체가 성공하면 성공 하나라도 실패하면 롤백!!
	// 회원가입이 정상적으로 잘 작동되면 1을 리턴 아니면 -1을 리턴
	public int save(User user) {
		String encodePassword = encode.encode(user.getPassword()); // 해쉬 암호화됨
		user.setPassword(encodePassword);
		user.setRole(RoleType.USER);
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입() : " + e.getMessage());
		}
		return -1;
	}

	// ===============================================
// 로그인
//	@Transactional(readOnly = true) // select 할때 트렌젝션 시작, 서비스 종료시에 트렌젝션 종료 (정합성)
//	public User login(User user) {
//		return userRepository.findByusernameAndPassword(user.getUsername(), user.getPassword());
//	}

	// ===============================================
	// 회원 수정
	@Transactional
	public void update(User user) {
		User getUser = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원수정이 실패했습니다.");
		});
		String encodePassword = encode.encode(user.getPassword()); // 해쉬 암호화됨
		getUser.setPassword(encodePassword);
		getUser.setEmail(user.getEmail());

	}
}
