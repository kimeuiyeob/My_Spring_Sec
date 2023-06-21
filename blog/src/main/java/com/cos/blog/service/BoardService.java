package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	// 글 쓰기
	@Transactional
	public void save(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
	}

	// 전체 조회
	public Page<Board> sellectAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

}
