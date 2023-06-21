package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// (@AuthenticationPrincipal PrincipalDetail principal)// 컨트롤러에서 세션 찾는방법
	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		// 컨트롤러에서 해당 jsp페이지로 값을 가져가기 위해서는 Model객체가 필요하다.
		
//		System.out.println("로그인 사용자 아이디 : " + principal);
		model.addAttribute("boardList", boardService.sellectAll(pageable));
		return "index"; // 컨트롤러는 리턴할때 viewResolver가 작동한다.
	}

	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}