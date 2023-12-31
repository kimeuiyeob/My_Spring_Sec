package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @getter, @setter 동시에 쓸수 있는 어노테이션
//@AllArgsConstructor // 모든 필드를 사용할수 있는 생성자
@NoArgsConstructor // 기본 생성자
public class Member {

	// 객체 지향에서 필드를 private 하는이유
	// 외부에서 다이렉트로 필드에 접근을 못하게 한다.
	// 메서드를 통해서 해당값을 수정하게 해야한다.
	private int id;
	private String userName;
	private String passWord;
	private String email;
	
	
	//@AllArgsConstructor 만들어준다.
	@Builder
	public Member(int id, String userName, String passWord, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
	}
	
}
