package com.douzone.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")
	private int count;
	
	@ManyToOne(fetch=FetchType.EAGER)  //Many= Board, User=One 여러개의 게시물은 한명에 의해 쓸수 있다 
	@JoinColumn(name="userid")
	private User user; //DB는 오브젝트를 저장할 수 없다 . FK,자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다.(난 FK키가 아니)DB에 컬럼 만들지 말라.. 
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;

}
