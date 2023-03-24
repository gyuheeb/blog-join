
package com.douzone.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		return "/home.html";
	}
	@GetMapping("/temp/img")
	public String tempimg() {
		return "/c.jpg";
	}
	@GetMapping("/temp/jsp")
	public String tempjsp() {
		//prefix:/WEB-INF/views/
		//suffix:.jsp
		return "test";
	} //동적인 파일이라 못 찾음
}
