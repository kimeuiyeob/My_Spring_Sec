package com.cos.blog.dto;

import lombok.Data;

@Data
public class ReplyDto {

	private int userId;
	private int boardId;
	private String content;
	
}
