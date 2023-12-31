package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
//스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다. -> 즉 여기선 UserDetails상속받은 PrincipalDetail 객체가 저장된다.
@Getter
public class PrincipalDetail implements UserDetails {

	private User user; // 객체를 품고있는거 -> 콤포지션

	public PrincipalDetail(User user) {
		this.user = user;
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
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았는지 리턴한다. (true : 만료 안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 계정이 잠겨있는지 않았는지 리턴한다. (true : 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // 비밀번호가 만료되지 않았는지 리턴한다(true: 만려안됨)
		return true;
	}

	@Override
	public boolean isEnabled() {// 계정이 활성화 (사용 가능)인지 리턴한다. (true : 활성화)
		return true;
	}

	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을수 있어서 루프를 돌려야 하는데 우리는 한개만!!)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		// ROLE_ 이건 규칙이라고 생각하자!! ex) ROLE_USER 이렇게 리턴해줘야한다.
		collectors.add(() -> {
			return "ROLE_" + user.getRole();
		});
		return collectors;
	}

}
