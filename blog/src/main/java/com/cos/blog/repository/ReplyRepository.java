package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	@Modifying
	@Query(value = "insert into reply (userId, boardId, content, createDate) values (?1, ?2, ?3, now())", nativeQuery = true)
	// ReplyDto필드값이 순서대로 ?로 들어가니까 순서를 잘 맞춰줘야한다.
	void saveReply(int userId, int boardId, String content);
}
