$(function() {
	// 초기세팅
	$('#payment-amount').text('￦'+priceToString($('#payment-amount').text())+'원');
	var cnt = $('.orderList-tr:last').index();
	for(var i=0; i<cnt; i++){
		$('.totalPrice').eq(i).text('￦'+priceToString($('.totalPrice').eq(i).text())+'원');	
	}
})

// 매출전표
function paymentOpen(url){
	var nWidth = "500";
	var nHeight = "850";
	  
	// 듀얼 모니터 고려한 윈도우 띄우기
	var curX = window.screenLeft;
	var curY = window.screenTop;
	var curWidth = document.body.clientWidth;
	var curHeight = document.body.clientHeight;
	var nLeft = curX + (curWidth / 2) - (nWidth / 2);
	var nTop = 70
	var strOption = "";
	strOption += "left=" + nLeft + "px,";
	strOption += "top=" + nTop + "px,";
	strOption += "width=" + nWidth + "px,";
	strOption += "height=" + nHeight + "px,";
	window.open(url, "KakaoPay", strOption)
}
