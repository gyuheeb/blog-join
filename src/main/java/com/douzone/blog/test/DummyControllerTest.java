package com.douzone.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.douzone.blog.model.RoleType;
import com.douzone.blog.model.User;
import com.douzone.blog.repository.UserRepository;
//html파일이 아니라 data를 리턴해주는 controller= RestController
@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
//	save함수는 id를 전달하지 않으며 insert를 해주고
//	save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고 
//	save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해줌.
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
			
		}catch(Exception e) {
			
			return "삭제에 실패하였습니다. 해당 id는 db에 없습니다.";
		}
		
		
		return "삭제되었습니다. id" +id;
		
	}
	 
	//email,password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser){ //json 데이터를 요청 -> Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌  )
		System.out.println("id" + id);
		System.out.println("password" + requestUser.getPassword());
		System.out.println("email" + requestUser.getEmail());
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
			
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		//userRepository.save(user);
		return null;
	}

	@GetMapping("/dummy/users")
	public List<User>list(){
		return userRepository.findAll();
	}
	//한페이지당 두건의 데이터를 리턴
	@GetMapping("/dummy/user")
	public List<User>pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id}주소로 파마레터를 전달 받을 수 있음
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
//      람다식			
//		User user =userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id:" +id);
			}
		});
		//요청:웹브라우저
		//user 객체 = 자바오브젝트
		//@RestController가 data로 리턴해주기 때문에 웹브라우저가 이해할 수 있는 데이터로 *변환이 필요하다 => JSON
		//스프링부트= MessageConverter이라는 애가 응답시 자동 작동
		//만약에 자바오브젝트를 리턴하게 되면  MessageConverter가 Jackson라이브러리를 호출해서 
		//user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {  //key=value(약속된 규칙..)
		System.out.println("username: "+ user.getUsername());
		System.out.println("password: "+ user.getPassword());
		System.out.println("email: "+ user.getEmail());
		System.out.println("role: "+ user.getRole());
		System.out.println("createDAte"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
