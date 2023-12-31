package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // <= 어떤곳에서도 해당 예외발생시 여기로 온다.
@RestController
//예외 발생시 처리하는 함수
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalArgumentException.class) // IllegalArgumentException발생시 해당 메서드를 타게된다.
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

//	@ExceptionHandler(value = Exception.class) // 모든 발생시 해당 메서드 타게된다.
//	public String handleArgumentException(Exception e) {
//		return "<h1>" + e.getMessage() + "</h1>";
//	}
}
