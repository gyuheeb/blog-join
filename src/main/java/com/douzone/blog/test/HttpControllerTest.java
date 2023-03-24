package com.douzone.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//사용자가 요청->응답 (HTML파일)
//@Controller

//사용자가 요청 -> 응답 (Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpController Test :";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("gyuhee4529@naver").build();
		System.out.println(TAG +"getter"+ m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG +"setter"+ m.getUsername());
		return "lombok test 완료";
	}
	
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
	
		return "get요청: "+m.getId()+","+m.getUsername()+m.getPassword();
	}
	@PostMapping("/http/post")
	public String PostTest(@RequestBody Member m) {
		return "Post 요청"+m.getId()+","+m.getUsername()+m.getPassword();
	}
	@PutMapping("/http/put")
	public String PutTest(@RequestBody Member m) {
		return "put 요청"+m.getId()+","+m.getUsername()+m.getPassword();
	}
	@DeleteMapping("/http/delete")
	public String DeleteTest() {
		return "delete 요청";
	}
}
