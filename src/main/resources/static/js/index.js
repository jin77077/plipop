$(function() {
	/*안내팝업*/
	window.open('popup.html', 'pop01', 'top=10, left=10, width=300, height=350, status=no, menubar=no, directories=no, scrollbars=no, toolbar=no, resizable=no');
	
	/*초기설정*/
	var test = ['best','new','outer','top','pants','shoes'];
	for(var i in test){
		var index = $('.'+test[i]+'-component:last').index()
		for(var j=0; j<=index; j++){
			$('.'+test[i]+'-component .card-consumerPrice').eq(j).text('￦' + priceToString($('.'+test[i]+'-component .card-consumerPrice').eq(j).text()) + '원');
			$('.'+test[i]+'-component .card-salePrice').eq(j).text('￦' + priceToString($('.'+test[i]+'-component .card-salePrice').eq(j).text()) + '원');
		}
	}
	
	/*BEST 사진 이미지 변경*/
	var arrayList = [];
	for(var i=0; i<= $('.best-component:last').index(); i++){
		var category = $('.best-component').children('.replaceCategory').eq(i).val();
		var productNo = $('.best-component').children('.replaceNo').eq(i).val();
		var mainImage = $('.best-component').children('.replaceMainImage').eq(i).val();
		var src1 = '/img/product/'+category+'/'+productNo+'/'+mainImage;
		var src2 = '/img/product/'+category+'/'+productNo+'/'+productNo+'_replace.jpg';
		var data = {
			src1 : src1,
			src2 : src2
		}
		arrayList.push(data)
	}
	var current ='src1';
	var cnt = 0;
	var timer = setInterval(function(){
		cnt++;
		if(current == 'src1'){
			current = 'src2';
			for(j=0; j<arrayList.length; j++){
				$('.best-component').eq(j).children().children('.card-image').attr("src",arrayList[j].src2)
				$('.best-component').eq(j).children().children().children('.card-image').attr("src",arrayList[j].src2)
			}
		}
		else {
			current = 'src1';
			for(j=0; j<arrayList.length; j++){
				$('.best-component').eq(j).children().children('.card-image').attr("src",arrayList[j].src1)
				$('.best-component').eq(j).children().children().children('.card-image').attr("src",arrayList[j].src1)
			}
		}
	},2500)
	
	
	
	/* slick 설정 */
	$('#slider-div').slick({
		slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
		infinite: true,     //무한 반복 옵션     
		slidesToShow: 1,        // 한 화면에 보여질 컨텐츠 개수
		slidesToScroll: 1,        //스크롤 한번에 움직일 컨텐츠 개수
		speed: 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
		arrows: true,         // 옆으로 이동하는 화살표 표시 여부
		dots: true,         // 스크롤바 아래 점으로 페이지네이션 여부
		autoplay: true,            // 자동 스크롤 사용 여부
		autoplaySpeed: 10000,         // 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
		pauseOnHover: true,        // 슬라이드 이동    시 마우스 호버하면 슬라이더 멈추게 설정
		vertical: false,        // 세로 방향 슬라이드 옵션
		prevArrow: "<button type='button' class='slick-prev'>Previous</button>",        // 이전 화살표 모양 설정
		nextArrow: "<button type='button' class='slick-next'>Next</button>",        // 다음 화살표 모양 설정
		dotsClass: "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
		draggable: true,     //드래그 가능 여부 
	});
	
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
 
function popupClose() {
	window.close();
}
 
 


