package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne // 여러개의 댓글은 하나의 게시글에 존재
	@JoinColumn(name = "boardId") //컬럼명은 boardId로 FK를 지정한다.
	private Board board; // Board테이블이랑 연관관계
	
	@ManyToOne //여러개의 댓글은 한명의 유저가 달수있다.
	@JoinColumn(name = "userId") 
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
