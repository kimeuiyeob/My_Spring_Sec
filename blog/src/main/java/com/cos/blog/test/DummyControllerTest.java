package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html파일을 리턴 -> Controller
//data를 리턴 -> RestController
@RestController
public class DummyControllerTest {

	// DummyControllerTest클래스가 메모리에 뜰때 UserRepository도 띄어준다.
	// 이미 repository는 Bean으로 주입되었기때문에 바로 사용하면 된다. => 의존성 주입 (DI)
	@Autowired
	private UserRepository userRepository;

//	======================================================
	// 값 넣기
	@PostMapping("/dummy/join")
	public String join(User user) {
		// messageConverter가 requestBody에 담겨있는 키값이랑 해당 객체의
		// 필드값이랑 동일하면 자동 매핑을 시켜서 해당 값을 받을수 있다.
		user.setRole(RoleType.USER); // <= enum을 활용하여 오타방지와 확실한 타입을 셋해줄수있다.
		userRepository.save(user);
		return "회원 가입 완료";
	}

//	======================================================
	// 전체 조회
	@GetMapping("/dummy/users")
	public List<User> sellectAll() {
		List<User> listUser = userRepository.findAll();
		return listUser;
	}

//	======================================================
	// 전체 조회 (페이지 추가)
	@GetMapping("/dummy/user")
	// 페이징 처리 -> size = 2개씩 들고오기, sort = 아이디로 정렬 (DESC,오름차순)
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//		Page<User> users = userRepository.findAll(pageable);
		// http:8000/dummy/user?page= <-이런식으로 쿼리스트링으로 페이지값을 받아와서 출력가능하다.
		List<User> users = userRepository.findAll(pageable).getContent(); // 페이지 관련된것두 가져와서 content만 가져오기
		return users;
	}

//	======================================================
	// 한명 조회
	@GetMapping("/dummy/user/{id}") // url { } 이렇게 값을 받는거는 @PathVariable이다.
	public User sellectOne(@PathVariable int id) {

		// 리턴타입이 Optional인 이유!
		// 해당 아이디값이 DB에 없어서 못찾아오게되면 user가 null이 될거 아냐?
		// 그럼 return 이 null일텐데 그럼 프로그램이 문제가 생길수 있잖아...
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!

//		User user = userRepository.findById(id).get(); //.get()은 null일수가 없으니까 Optional타입을 풀어버릴꺼야! <= 위험할수있다.

		// 만약 findById가 null 일시에는 유저 빈객체를 return한다.
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});

		// 만약 해당 id가 없을시에는 IllegalArgumentException에러를 발생시키고 아래문구를 출력한다.
		// 그래서 AOP로 해당 에러가 발생하면 해당 에러페이지로 보내 처리할수 있다.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});

		// 람다식 활용
//		User user = userRepository.findById(id).orElseThrow(() -> {
//				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//		});

		// 변환 (웹브라우저 이해할수 있는 데이터) -> Json (Gson 라이브러리로 변환 가능)
		// 하지만 스프링 부트 -> messageConverter가 응답시에는 자동으로 자바 객체를
		// Jackson 라이브러리를 호출해서 json으로 변환하여 브라우저에게 던져준다.
		return user;
	}

//	======================================================
	// 한명 수정
	@Transactional
	@PutMapping("/dummy/user/{id}")
	// Json데이터 요청 -> 자바 객체를 MessageConverter의 Jackson라이브러리가 변환해서 받아준다.
	public User updateUser(@PathVariable int id, @RequestBody User user) {
 
		// JPA에서 save는 insert , update 둘다 가능하다.
		// 아이디가 있으면 해당 아이디를 찾아 update 없으면 insert방식으로 들어간다.
		// 문제 -> 해당 아이디를 찾아 모든값을 다시 update하지 않는이상 안들어간값은 null로 들어가버린다.
		// 그래서 해당 아이디로 먼저 해당 값들을 다 찾아온다음에 수정할것들만 바까서 save하는방향으로 가야한다.

		User updateUser = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하셨습니다. id : " + id);
		});

		updateUser.setEmail(user.getEmail());
		updateUser.setPassword(user.getPassword());

		// userRepository.save(updateUser);

		// @Transactional 더티 체킹 -> userRepository.save(updateUser)하지 않아도 수정이 완료됬다.
		// 우선 findById()로 해당 정보를 찾아오게될때 영속성컨텍스트안에 있는 1차캐시안에 해당 값이 저장된다.
		// 그리고 해당 메서드 안에서 set으로 해당값을 수정했다. 메서드가 종료시 @Transactional 어노테이션은 자동으로 커밋을 시켜서
		// 1차캐시안에 영속화되어있는 값이 바뀌면서 DB에 값을 수정하는것이다.
		// set을 하지 않았다면 변경된게 없으니까 아무런 일이 벌어지지 않을것이고 set을 하여서 값을 수정하였다면
		// @Transactional 어노테이션이 더티 체킹을 하여서 변경감지가 되면 해당 값을 수정하고 커밋을 날려준다.

		// 즉 중간에 영속성컨텍스트라는 영역이 더 있다고 생각하자!!

		return user;
	}

//	======================================================
	// 한명 삭제
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		// deleteById는 void리턴 타입이여서 .orElseThrow()의 내장 함수가 없다.
		// 그래서 에러 발생시 try catch로 해당 에러를 잡아 문구를 리턴한다.
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하셨습니다. id : " + id;
		}
		return "삭제에 성공했습니다. id : " + id;
	}
//	======================================================
}
