package com.douzone.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.blog.dto.ResponseDto;
import com.douzone.blog.model.RoleType;
import com.douzone.blog.model.User;
import com.douzone.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("User ApiController: save 호출됨");
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
		
	}
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("User ApiController: login호출됨");
		User principal = userService.로그인(user);
		
		if(principal != null) {
			session.setAttribute("principal",principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
}
