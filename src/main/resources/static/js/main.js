$(function() {

	/*일정이상 스크롤이 내려가면 클래스를 추가하거나 제거해주는 FUNCTION*/
	var lastScroll = 0;
	$(window).scroll(function(event) {
		var scroll = $(this).scrollTop();
		if (scroll > 150) {
			//이벤트를 적용시킬 스크롤 높이
			$(".category-bar").addClass("fiexd");
			$("section").addClass("sectionFiexd");
		}
		else {
			$(".category-bar").removeClass("fiexd");
			$("section").removeClass("sectionFiexd");
		}
		lastScroll = scroll;
	});

	/*커뮤니티 드롭다운*/
	$('#drop-Btn').hover(function() {
		$('#drop-content').css('display', 'block');
	}, function() {
		$('#drop-content').hover(function() {
			$('#drop-content').css('display', 'block');
		}, function() {
			$('#drop-content').css('display', 'none');
		});
	});
});

/*가격 천단위 구분기호 (정규식 사용)*/
function priceToString(price) {
	return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

/*null 체크 함수*/
function isEmpty(str) {

	if (typeof str == "undefined" || str == null || str == "")
		return true;
	else
		return false;
}

/*가격 TRIM*/
function trimPrice(price){
	return Number(price.replace('￦', '').replace('원', '').replace(',', ''));
}

 $(function () {
    $("#slide-open").click(function () {
        if ($("#burgur").hasClass('on')) {
            $("#burgur").removeClass('on');
            $("#slide").removeClass('on');

        } else {
            $("#burgur").addClass('on');
            $("#slide").addClass('on');
        }
    });

});



