let index = {

	init: function() {
		//on은 이벤트를 만들어준다.
		//첫번째 파라미터에는 해당 이벤트의 이름 두번째 파라미터는 무엇을 할껀지를 적으면된다.
		$("#btn-save").on("click", () => {
			//화살표 함수 function(){} 안쓰고 이걸 쓰는 이유는 this를 바인딩하기 위해서
			this.save();
		});

		$("#btn-update").on("click", () => {
			this.update();
		});

	},
	/*=========================================================================*/
	//회원 가입
	save: function() {

		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//ajax 호출시 default 비동기 호출
		//ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
		$.ajax({
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //data JS객체를 json형식을 바꾼다.
			contentType: "application/json; charset=utf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json",  //요청을 서버로 해서 응답이 왔을때 기본적으로 모든게 문자열, 하지만 생긴게 json이라면 JS객체로 변경해준다.
			//dataType 안적어된다. => ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 객체로 변환해준다.
		}).done(function(resp) { //응답을 결과를 받는다. <= 해당 컨트롤러의 리턴값이 넘어온다.
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert("회원가입 실패하였습니다.");
		});
	},

	/*=========================================================================*/
	//회원 정보 수정
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			type: "put",
			url: "/user/update",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json",
		}).done(function(resp) {
			alert("회원정보 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert("회원정보 수정이 실패하였습니다.");
		});
	},

}

index.init();