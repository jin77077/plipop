$(function() {
	/*초기설정*/
	var test = ['best','new','outer','top','pants','shoes','search'];
	for(var i in test){
		var index = $('.'+test[i]+'-component:last').index()
		for(var j=0; j<=index; j++){
			$('.'+test[i]+'-component .card-consumerPrice').eq(j).text('￦' + priceToString($('.'+test[i]+'-component .card-consumerPrice').eq(j).text()) + '원');
			$('.'+test[i]+'-component .card-salePrice').eq(j).text('￦' + priceToString($('.'+test[i]+'-component .card-salePrice').eq(j).text()) + '원');
		}
	}
	
	/*상품 마우스오버시 변경효과*/
	$('.card-image').hover(function(){
		var className = $(this).parent().parent().parent().children('.card-move-discountRate');
		var index = $(this).parent().parent().parent().index();
        className.css('opacity',1);
        className.css('top','36px');
        className.css('transition','all ease .5s 0s');
    }, function() {
		var className = $(this).parent().parent().parent().children('.card-move-discountRate');
		var index = $(this).parent().parent().parent().index();
        className.css('opacity',0);
        className.css('top','23px');
    });
	
 });
 
 /*검색버튼*/
 function search(){
	/*상품명 필수입력처리*/
	if($('.search').val() == null || $('.search').val() == '' ){
		alert("상품명을 입력하셔야 합니다");
		$("input[name='search']").focus();
		return;
	}
	/*최소금액 입력시 최대금액 기입필수처리*/
	if($("input[name='minPrice']").val() > 0){
		if($("input[name='maxPrice']").val() <= 0){
			alert("최대금액을 입력하셔야 합니다");
			$("input[name='maxPrice']").focus();
			return;
		}
		else if($("input[name='maxPrice']").val() < $("input[name='minPrice']").val()){
			alert("최대금액은 최소금액보다 작을 수 없습니다");
			$("input[name='maxPrice']").focus();
			return;
		}
	}
	$('.searchForm').submit();
}
 


