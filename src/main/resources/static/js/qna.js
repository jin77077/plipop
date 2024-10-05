$(function() {
	/*비밀글, 공개글 설정에 따른 비밀번호 input 활성화 여부*/
	$('#publicBtn').click(function() {
		$('#password').attr("disabled", true);
		$('#password').val("");
	});
	
	$('#secretBtn').click(function() {
		$('#password').attr("disabled", false);
	});

/*Qna 비밀번호 확인*/
	$('#chkPassword').click(function(){
		var pw = $('#password').val();
		$.ajax({
			type: "GET",
			url: "/community/chkPassword",
			data : { password : $('#password').val(), boardNum : $('#boardNum').val(), role : $('#role').val()},
			success: function(res) {
				if(res == 1){
					location.href="/community/qna/"+$('#boardNum').val()+"?chkSecret=public";
				} else {
					alert("비밀번호가 틀립니다.")
				}
			},
			error: function(data) {
				console.log("error 발생"+data)
			}
		})
	});
});

// QNA 글작성, 수정 SUBMIT
function qnaSubmit(){
	if($(':radio[name="chkSecret"]:checked').val() == 'secret'){
		if ($('#password').val() == "") {
		alert("패스워드를 입력하여 주시기 바랍니다.");  
		} else {
			$("form").submit();
		}
	} else {
		$("form").submit();
	}
}

// QNA 글삭제
function deleteQna(){
	var chk = confirm("삭제하시겠습니까?");
	if(chk){
		$.ajax({
			type: "POST",
			url: "/community/deleteQna",
			data : {boardNum : $('#boardNum').val()},
			success: function(res) {
				location.href="/community/qna";
			},
			error: function(data) {
				console.log("error 발생"+data)
			}
		})		
	}
};

