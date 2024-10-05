$(function() {
	/*회원가입 아이디 사용가능여부 조회*/
	if ($("#password").val() == '' || $("#password").val() == "null") {
		$(".chkPwd").html("비밀번호를 입력해주세요.")
	}
	$("#password").focusout(function() {
		if ($("#password").val() == '' || $("#password").val() == "null") {
			$(".chkPwd").html("비밀번호를 입력해주세요.")
		}
		else {
			$(".chkPwd").html("")
		}
	})
	
	/*비밀번호 체크*/
	$("#passwordCheck").focusout(function() {
		if ($('#passwordCheck').val() == '') {
			$(".chkPwdMatch").html("비밀번호 확인을 입력해주세요.").css({ "color": "#575757" })
		}
		else if ($('#password').val() != $('#passwordCheck').val()) {
			$(".chkPwdMatch").html("비밀번호가 다릅니다.").css({ "color": "red" })
		}
		else {
			$(".chkPwdMatch").html("비밀번호가 일치합니다.").css({ "color": "green" })
		}
	})
	
	/*이메일 제공자 설정*/
	$('#emailProviderSelect').change(function(){
		var provider = $('#emailProviderSelect').val();
		if(provider != '*'){
			$('#emailProvider').val(provider);
			$('#emailProvider').attr('readOnly',true);
		}
		else {
			$('#emailProvider').val('');
			$('#emailProvider').attr('readOnly',false);
		}
	})
});

// 생년월일 selectBox 생성 
$(document).ready(function(){
	var now = new Date(); 
	if(birthYear != null){
		var year = birthYear
	}
	else{
		var year = now.getFullYear();
	}
	if(birthMonth != null){
		var mon = birthMonth > 9 ? birthMonth : "0"+birthMonth 	
	}
	else{
		var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);	
	}
	if(birthDay != null){
		var day = birthDay > 9 ? birthDay : "0"+birthDay;
	}
	else{
		var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());	
	}
	 //년도 selectbox만들기 
	 for(var i = 1900 ; i <= year ; i++) {
		$('#year').append('<option value="' + i + '">' + i + '년</option>'); 
	 } 
	 // 월별 selectbox 만들기 
	 for(var i=1; i <= 12; i++) {
		var mm = i > 9 ? i : "0"+i ;
		$('#month').append('<option value="' + mm + '">' + mm + '월</option>');
	 } 
	 // 일별 selectbox 만들기 
	 for(var i=1; i <= 31; i++) {
		var dd = i > 9 ? i : "0"+i ;
		$('#day').append('<option value="' + dd + '">' + dd+ '일</option>');
	 } 
     $("#year > option[value="+year+"]").attr("selected", "true");
	 $("#month > option[value="+mon+"]").attr("selected", "true");
	 $("#day > option[value="+day+"]").attr("selected", "true"); 
})


$(function() {
	//모달을 전역변수로 선언
	var modalContents = $(".modal-contents");
	var modal = $("#defaultModal");

	// modal 창 close 기능
	$("#close").click(function() {
		$("#defaultModal").modal("hide");
	});


	// 값 검증 기능
	$('.onlyAlphabetAndNumber').keyup(function(event) {
		if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
			var inputVal = $(this).val();
			$(this).val($(this).val().replace(/[^_a-z0-9]/gi, '')); //_(underscore), 영어, 숫자만 가능
		}
	});

	$(".onlyHangul").keyup(function(event) {
		if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[a-z0-9]/gi, ''));
		}
	});

	$(".onlyNumber").keyup(function(event) {
		if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi, ''));
		}
	});

	//------- validation 검사
	$("form").submit(function(event) {

		var provision = $('#provision');
		var memberInfo = $('#memberInfo');
		var divId = $('#divId');
		var divPassword = $('#divPassword');
		var divPasswordCheck = $('#divPasswordCheck');
		var divName = $('#divName');
		var divNickname = $('#divNickname');
		var divEmail = $('#divEmail');
		var divPhoneNumber = $('#divPhoneNumber');

		//패스워드 검사
		if ($('#password').val() == "") {
			modalContents.text("패스워드를 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divPassword.removeClass("has-success");
			divPassword.addClass("has-error");
			$('#password').focus();
			return false;
		} else {
			divPassword.removeClass("has-error");
			divPassword.addClass("has-success");
		}

		//패스워드 확인
		if ($('#passwordCheck').val() == "") {
			modalContents.text("패스워드 확인을 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divPasswordCheck.removeClass("has-success");
			divPasswordCheck.addClass("has-error");
			$('#passwordCheck').focus();
			return false;
		} else {
			divPasswordCheck.removeClass("has-error");
			divPasswordCheck.addClass("has-success");
		}

		//패스워드 비교
		if ($('#password').val() != $('#passwordCheck').val() || $('#passwordCheck').val() == "") {
			modalContents.text("패스워드가 일치하지 않습니다.");
			modal.modal('show');

			divPasswordCheck.removeClass("has-success");
			divPasswordCheck.addClass("has-error");
			$('#passwordCheck').focus();
			return false;
		} else {
			divPasswordCheck.removeClass("has-error");
			divPasswordCheck.addClass("has-success");
		}

		//이름
		if ($('#name').val() == "") {
			modalContents.text("이름을 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divName.removeClass("has-success");
			divName.addClass("has-error");
			$('#name').focus();
			return false;
		} else {
			divName.removeClass("has-error");
			divName.addClass("has-success");
		}

		//우편번호
		if ($('#zipCode').val() == "") {
			modalContents.text("우편번호를 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divPhoneNumber.removeClass("has-success");
			divPhoneNumber.addClass("has-error");
			$('#zipCode').focus();
			return false;
		} else {
			divPhoneNumber.removeClass("has-error");
			divPhoneNumber.addClass("has-success");
		}
		// 기본주소
		if ($('#address').val() == "") {
			modalContents.text("기본주소를 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divPhoneNumber.removeClass("has-success");
			divPhoneNumber.addClass("has-error");
			$('#address').focus();
			return false;
		} else {
			divPhoneNumber.removeClass("has-error");
			divPhoneNumber.addClass("has-success");
		}
		// 상세주소
		if ($('#detailAddress').val() == "") {
			modalContents.text("상세주소를 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divPhoneNumber.removeClass("has-success");
			divPhoneNumber.addClass("has-error");
			$('#detailAddress').focus();
			return false;
		} else {
			divPhoneNumber.removeClass("has-error");
			divPhoneNumber.addClass("has-success");
		}
		
	});
});

/*다음 주소API 호출*/
function findAddr() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = '';
			if (data.userSelectedType === 'R') {
				addr = data.roadAddress
			}
			else {
				addr = data.jibunAddress;
			}
			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipCode').value = data.zonecode;
			document.getElementById("address").value = addr;
		}
	}).open();
};