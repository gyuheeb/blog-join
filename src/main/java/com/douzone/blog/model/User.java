package com.douzone.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
//@DynamicInsert : insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id //pribmary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id ;
	
	@Column(nullable=false, length=30,unique=true)
	private String username;
	
	@Column(nullable=false, length=30)
	private String password;
	
	@Column(nullable=false, length=100)
	private String email;
	
	//@ColumnDefault("'user'")
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum쓰는게 좋다. -> 어떤 데이터에 대한 도메인설정이 가능하기 때문
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
	
	
}
