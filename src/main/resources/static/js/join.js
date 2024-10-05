$(function() {
	// 회원가입 아이디 사용가능여부 조회
	$("#id").focusout(function() {
		var value = $("#id").val();
		if (value == null || value == "") {
			// userId 입력값없을 시 비움
			$(".chkId").html('');
		}
		else {
			$.ajax({
				type: "GET",
				url: "/users/findId",
				data: { userId: value },
				dataType: "json",
				success: function(res) {
					// hidden으로 체크하는거 넣어야됨 => 아이디 중복체크여부
					// 아이디 있음
					if (res == 1) {
						$(".chkId").html("이미 존재하는 아이디 입니다.")
							.css({ "color": "red" })
						$(".chkId").addClass("ErrorId");
					}
					// 아이디 없음
					else {
						$(".chkId").html("사용 가능한 아이디 입니다.")
							.css({ "color": "green" })
						$(".chkId").removeClass("ErrorId");
					}
				},
				error: function(data) {
					console.log("error 발생")
				}
			})
		}
	})

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
	
	// 이메일 제공자 설정
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

// 전체동의 버튼
$(function() {
	var chkList = $("input[name=xxx]");

	$("#allChecked").on("click", function() {
		if ($(this).is(":checked")) {
			chkList.prop("checked", true);
		} else
			chkList.prop("checked", false);
	});
});

// 생년월일 selectBox 생성 
$(document).ready(function(){
	var now = new Date(); 
	var year = now.getFullYear();
	var mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1);	
	var day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate());	
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

	//------- 검사하여 상태를 class에 적용
	$('#id').keyup(function(event) {

		var divId = $('#divId');
		if ($('#id').val() == "") {
			divId.removeClass("has-success");
			divId.addClass("has-error");
		} else {
			divId.removeClass("has-error");
			divId.addClass("has-success");
		}
	});

	$('#password').keyup(function(event) {

		var divPassword = $('#divPassword');

		if ($('#password').val() == "") {
			divPassword.removeClass("has-success");
			divPassword.addClass("has-error");
		} else {
			divPassword.removeClass("has-error");
			divPassword.addClass("has-success");
		}
	});

	$('#passwordCheck').keyup(function(event) {

		var passwordCheck = $('#passwordCheck').val();
		var password = $('#password').val();
		var divPasswordCheck = $('#divPasswordCheck');

		if ((passwordCheck == "") || (password != passwordCheck)) {
			divPasswordCheck.removeClass("has-success");
			divPasswordCheck.addClass("has-error");
		} else {
			divPasswordCheck.removeClass("has-error");
			divPasswordCheck.addClass("has-success");
		}
	});

	$('#name').keyup(function(event) {

		var divName = $('#divName');

		if ($.trim($('#name').val()) == "") {
			divName.removeClass("has-success");
			divName.addClass("has-error");
		} else {
			divName.removeClass("has-error");
			divName.addClass("has-success");
		}
	});

	$('#nickname').keyup(function(event) {

		var divNickname = $('#divNickname');

		if ($.trim($('#nickname').val()) == "") {
			divNickname.removeClass("has-success");
			divNickname.addClass("has-error");
		} else {
			divNickname.removeClass("has-error");
			divNickname.addClass("has-success");
		}
	});

	$('#email').keyup(function(event) {

		var divEmail = $('#divEmail');

		if ($.trim($('#email').val()) == "") {
			divEmail.removeClass("has-success");
			divEmail.addClass("has-error");
		} else {
			divEmail.removeClass("has-error");
			divEmail.addClass("has-success");
		}
	});

	$('#phoneNumber').keyup(function(event) {

		var divPhoneNumber = $('#divPhoneNumber');

		if ($.trim($('#phoneNumber').val()) == "") {
			divPhoneNumber.removeClass("has-success");
			divPhoneNumber.addClass("has-error");
		} else {
			divPhoneNumber.removeClass("has-error");
			divPhoneNumber.addClass("has-success");
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

		//아이디 검사
		if ($('#id').val() == "") {
			modalContents.text("아이디를 입력하여 주시기 바랍니다.");
			modal.modal('show');

			divId.removeClass("has-success");
			divId.addClass("has-error");
			$('#id').focus();
			return false;
		} else {
			divId.removeClass("has-error");
			divId.addClass("has-success");
		}
		
		if($(".chkId").hasClass("ErrorId") === true) {
			modalContents.text("중복되지 않는 아이디로 수정해주시기 바랍니다.");
			modal.modal('show');

			divId.removeClass("has-success");
			divId.addClass("has-error");
			$('#id').focus();
			return false;
		} else {
			divId.removeClass("has-error");
			divId.addClass("has-success");
		}

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
		
		//회원가입약관
		if ($('#joinCheck:checked').val() != "Y") {
			modalContents.text("회원가입약관에 동의하여 주시기 바랍니다."); //모달 메시지 입력
			modal.modal('show'); //모달 띄우기

			provision.removeClass("has-success");
			provision.addClass("has-error");
			$('#joinCheck').focus();
			return false;
		} else {
			provision.removeClass("has-error");
			provision.addClass("has-success");
		}

		//개인정보취급방침
		if ($('#personCheck:checked').val() != "Y") {
			modalContents.text("개인정보취급방침에 동의하여 주시기 바랍니다.");
			modal.modal('show');

			memberInfo.removeClass("has-success");
			memberInfo.addClass("has-error");
			$('#personCheck').focus();
			return false;
		} else {
			memberInfo.removeClass("has-error");
			memberInfo.addClass("has-success");
		}
	});
});

/*다음 주소 API 호출*/
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