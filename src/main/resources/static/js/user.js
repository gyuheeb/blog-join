let index={
	init: function(){
		$("#btn-save").on
		("click",()=>{ //function(){}대신 ()=>{}를 사용하는 건 this를 바인딩하기 위해 
			this.save();
		});
		
	},
	save:function(){
		//alert("user의 save함수 호출됨");
		let data = {username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val()
		}
			//console.log(data);
			
			$.ajax({
				type: "POST",
				url: "/blog/api/user",
				data: JSON.stringify(data), //http body데이터
				contentType:"application/json; charset=utf-8",  //body데이터가 어떤 타입인지(MIME)
				dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 => javascript오브젝트로 변경해줌)
			}).done(function(resp){
				alert("회원가입이 완료도이었습니다.");
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});  //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
			
	},
	login:function(){
		//alert("user의 save함수 호출됨");
		let data = {username: $("#username").val(),
		password: $("#password").val()
		}
			//console.log(data);
			
			$.ajax({
				type: "POST",
				url: "/blog/api/user/login",
				data: JSON.stringify(data), //http body데이터
				contentType:"application/json; charset=utf-8",  //body데이터가 어떤 타입인지(MIME)
				dataType:"json" //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면 => javascript오브젝트로 변경해줌)
			}).done(function(resp){
				alert("로그인이 완료도이었습니다.");
				location.href="/blog";
			}).fail(function(error){
				alert(JSON.stringify(error));
			});  //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
			
	}

}

index.init();