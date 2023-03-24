package com.douzone.blog.model;

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

@Data //Getter,Setter
@NoArgsConstructor //Bean 생성자
@AllArgsConstructor //전체 생성자
@Builder//빌더패턴
@Entity //user클래스가 MYSQL에 테이블이 생성이 된다.
public class Reply {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;
	@Column(nullable = false, length=100)
	private String title;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne //여러개의 댓글은 하나의 게시글이 존재
	@JoinColumn(name="boardid")
	private Board board;

	@ManyToOne //여러개의 댓글을 하나의 유저가 쓸 수 있다.
	@JoinColumn(name="userid")
	private User user;
	
	@CreationTimestamp
	
	
	
	private Timestamp createDate;
}
