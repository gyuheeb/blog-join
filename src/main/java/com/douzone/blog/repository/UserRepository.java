package com.douzone.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.douzone.blog.model.User;

//DAO
//자동으로 Bean등록이 된다
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {
	
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username =?  And password = ?
	User findByUsernameAndPassword(String username,String password);
	
	
	
//	@Query(value = "SELECT * FROM user WHERE username =?1  And password = ?2",nativeQuery =true)
//	User login(String username, String password);

}
