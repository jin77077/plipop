$(function() {
	/*초기설정*/
	var test = ['best','new','outer','top','pants','shoes'];
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
 


