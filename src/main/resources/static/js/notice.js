function deleteNotice(){
	// 공지사항 글삭제
	var chk = confirm("삭제하시겠습니까?");
	if(chk){
		$.ajax({
		type: "POST",
		url: "/manager/deleteNotice",
		data : {boardNum : $('#boardNum').val()},
		success: function(res) {
			location.href="/community/notice";
		},
		error: function(data) {
			console.log("error 발생"+data)
		}
	})		
	}
};

		
