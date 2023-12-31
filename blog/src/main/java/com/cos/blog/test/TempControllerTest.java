package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		// 파일 기본 경로 : src/main/resources/static
		System.out.println("tempHome()");
		// 풀 경로 : src/main/resources/static/home.html
		return "/home.jsp";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("tempJsp()");
		// yml 설정해 놓은 경로
		//prefix: /WEB-INF/views/ -> prefix는 앞에 붙는 경로
		//suffix: .jspl    				    -> suffix는 뒤에 붙는 경로
		return "home";
	}
} 
