$(function() {
	// 관리자 페이지 CSS 변경
	$('.category-bar').addClass('hidden');
	$('.index-header').addClass('hidden');
	$('.searchBar').addClass('hidden');
	$('.shopLogo').addClass('hidden');
	$('.shopDescription').addClass('hidden');
	$('footer').addClass('hidden');
	$('.header_top').css({ 'border-bottom': '1px solid #5c5c5c', 'background-color': '#5c5c5c' });
	/*$('.navlog img').css({ 'width': '59%', 'top': '15px', 'left': '540px' })*/
	$('.guide_nav').css({ 'position': 'relative', 'right': '-304px' })

	/*관리자 메뉴 탭설정*/
	var $menuEle = $('dt');
	$menuEle.click(function() {
		var tab = $(this).children().text();
		$('dt').addClass('disableTab');
		$(this).removeClass('disableTab');
		$('dd').addClass('hidden');
		$(this).next().removeClass('hidden');
		if(tab =='상품등록'){
			console.log('상품등록 작업')
		}
		else if (tab =='상품관리'){
			console.log('상품관리 작업')
			$.ajax({             
		    type: "GET",          
		        url: "/manager/productAllList",        
		        success: function (data) { 
		        	console.log("상품목록 가져오기"+data)       
		        },          
		        error: function (e) {  
		            console.log("상품목록 가져오기 실패")      
		         }     
			})	
		}
		else if (tab =='회원관리'){
			console.log('회원관리 작업')
		}
		else if (tab =='주문관리'){
			console.log('주문관리 작업')
		}
	})

	/*관리자 메뉴 메인이미지 미리보기*/
	$('#input-image').change(function(event) {
		readImage(event.target)
	});

	/*관리자 메뉴 상세이미지 미리보기 설정*/
	$('#input-imgs').change(function(event) {
		handleImgsFilesSelect(event)
	});

	// 상품등록 할인율 변경시 판매가 수정
	$('#discountRate').change(function() {
		if ($('#consumerPrice').val() != null) {
			var consumerPrice = $('#consumerPrice').val();
			var discountRate = $('#discountRate').val();
			// (소비자가 / 할인율) / 100 후 소수점 버림 후 곱하기 100 = 100자리 밑으로 버림처림 [ex : 28199 -> 28100]  
			var salePrice = Math.floor((consumerPrice * (1 - (discountRate / 100)) / 100)) * 100;
			$('#salePrice').attr('value', salePrice);
		}
	})
	
	// 상품등록 소비자가 변경시 판매가 수정
	$('#consumerPrice').change(function() {
		if ($('#discountRate').val() != null) {
			var consumerPrice = $('#consumerPrice').val();
			var discountRate = $('#discountRate').val();
			// (소비자가 / 할인율) / 100 후 소수점 버림 후 곱하기 100 = 100자리 밑으로 버림처림 [ex : 28199 -> 28100]  
			var salePrice = Math.floor((consumerPrice * (1 - (discountRate / 100)) / 100)) * 100;
			$('#salePrice').attr('value', salePrice);
		}
	})
	
	// 상품수정 할인율 변경시 판매가 수정
	$('.update-discountRate').change(function() {
		var node = $(this).parent().parent('.update-tr')
		if ($('.update-consumerPrice').val() != null) {
			var consumerPrice = node.children().children('.update-consumerPrice').val();
			var discountRate = node.children().children('.update-discountRate').val();
			// (소비자가 / 할인율) / 100 후 소수점 버림 후 곱하기 100 = 100자리 밑으로 버림처림 [ex : 28199 -> 28100]  
			var salePrice = Math.floor((consumerPrice * (1 - (discountRate / 100)) / 100)) * 100;
			node.children().children('.update-salePrice').attr('value', salePrice);
		}
	})
	// 상품수정 소비자가 변경시 판매가 수정
	$('.update-consumerPrice').change(function() {
		var node = $(this).parent().parent('.update-tr')
		if ($('.update-discountRate').val() != null) {
			var consumerPrice = node.children().children('.update-consumerPrice').val();
			var discountRate = node.children().children('.update-discountRate').val();
			// (소비자가 / 할인율) / 100 후 소수점 버림 후 곱하기 100 = 100자리 밑으로 버림처림 [ex : 28199 -> 28100]  
			var salePrice = Math.floor((consumerPrice * (1 - (discountRate / 100)) / 100)) * 100;
			node.children().children('.update-salePrice').attr('value', salePrice);
		}
	})
	
	// 상품등록 컬러추가
	var colorList = [];
	$('#colorAdd').click(function() {
		if($('#colorInput').val() != ''){
			var color = $('#colorInput').val()
			colorList.push(color);
			var html = '<div class="listArea"><span class="nameArea">' + color + '</span><span id="colorRemoveBtn" class="cancelBtn">X</span></div>';
			$('#addColorList').append(html)
			$('#colorInput').val('')
			$('#colorInput').focus();
		}
		else {
			alert("공백을 입력할 수 없습니다.")
		}
	})
	
	// 상품등록 컬러삭제
	$(document).on('click', '#colorRemoveBtn', function(event) {
		event.target.parentElement.remove()
		var text = event.target.parentNode.innerText.replace('X','');
		colorList.pop('text')
	})
	
	// 상품등록 사이즈추가
	var sizeList = [];
	$('#sizeAdd').click(function() {
		if($('#sizeInput').val() != ''){
			var size = $('#sizeInput').val()
			sizeList.push(size);
			var html = '<div class="listArea"><span class="nameArea">' + size + '</span><span id="sizeRemoveBtn" class="cancelBtn">X</span></div>';
			$('#addSizeList').append(html)
			$('#sizeInput').val('')
			$('#sizeInput').focus();
		}
		else {
			alert("공백을 입력할 수 없습니다.")
		}
	})
	
	// 상품등록 사이즈삭제
	$(document).on('click', '#sizeRemoveBtn', function(event) {
		event.target.parentElement.remove()
		var text = event.target.parentNode.innerText.replace('X','');
		sizeList.pop('text')
	})
	
	// 상품등록 AJAX
	$('#productSubmit').click(function(){
		// 상품명
		if(!$('#productName').val()){
			alert("상품명을 입력하셔야 합니다.")
			$('#productName').focus();
			return;
		}
		// 소비자가
		if(!Number($('#consumerPrice').val())){
			alert("소비자가를 입력하셔야 합니다.")
			$('#consumerPrice').focus();
			return;
		}
		// 색상정보
		if(colorList.length == 0){
			alert("색상정보를 등록하셔야 합니다.")
			$('#colorInput').focus();
			return;
		}
		// 사이즈정보
		if(sizeList.length == 0){
			alert("사이즈 정보를 등록하셔야 합니다.")
			$('#sizeInput').focus();
			return;
		}
		// 메인이미지
		if(!$('#input-image')[0].files.length){
			alert("메인이미지를 등록하셔야 합니다.")
			$('#input-image').focus();
			return;
		}
		// 상세이미지
		if(!$('#input-imgs')[0].files.length){
			alert("상세이미지를 등록하셔야 합니다.")
			$('#input-imgs').focus();
			return;
		}
		var productName = $('#productName').val()
		var category = $('input[name="category"]:checked').val();
		var consumerPrice = Number($('#consumerPrice').val())
		var discountRate = Number($('#discountRate').val())
		var salePrice = Number($('#salePrice').val())
		var stock = Number($('#stock').val())
		var mainImage = document.getElementById('input-image').files[0].name;
		var files = [];
		var fileList = document.getElementById('input-imgs').files;
		for(var i = 0; i < fileList.length; i++){
			files.push(fileList[i].name);
		}
		var hot = $('.addBtn-table:first input:checkbox[name="hotYn"]').is(":checked")
		var mdPick = $('.addBtn-table:first input:checkbox[name="mdPickYn"]').is(":checked")
		var quick = $('.addBtn-table:first input:checkbox[name="quickYn"]').is(":checked")
		var best = $('.addBtn-table:first input:checkbox[name="bestYn"]').is(":checked")
		var sale = $('.addBtn-table:first input:checkbox[name="saleYn"]').is(":checked")
		
		$.ajax({             
	    	type: "POST",          
	        url: "/manager/productSave",        
	        data: {	
				'category'		: category,
				'productName'	: productName,
				'discountRate'	: discountRate,
				'consumerPrice'	: consumerPrice,
				'salePrice'		: salePrice,
				'stock'			: stock,
				'mainImage'		: mainImage,
				'files'			: files,
				'colorList'		: colorList,
				'sizeList'		: sizeList,
				'hotYn'			: hot,
				'mdPickYn'		: mdPick,
				'quickYn'		: quick,
				'bestYn'		: best,
				'saleYn'		: sale
			},          
	        success: function (data) { 
	        	console.log("상품등록 성공")       
	        	location.href="/";   
	        },          
	        error: function (e) {  
	            console.log("상품등록 실패")      
	         }     
		});  
	})
	
	/*상품수정*/
	$('.update-btn').click(function(){
		var node = $(this).parent().prevAll();
		var btnNode = $(this).parent().prev().children().children().children('tr:last').children();
		var productNo = node.children('.update-productNo').val();
		var productName = node.children('.update-productName').val();
		var consumerPrice = node.children('.update-consumerPrice').val();
		var discountRate = node.children('.update-discountRate').val();
		var salePrice = node.children('.update-salePrice').val();
		var stock = node.children('.update-stock').val();
		var hotYn = btnNode.children('input:checkbox[name="hotYn"]').is(':checked');
		var mdPickYn = btnNode.children('input:checkbox[name="mdPickYn"]').is(':checked');
		var quickYn = btnNode.children('input:checkbox[name="quickYn"]').is(':checked');
		var bestYn = btnNode.children('input:checkbox[name="bestYn"]').is(':checked');
		var saleYn = btnNode.children('input:checkbox[name="saleYn"]').is(':checked');
		$.ajax({             
	    	type: "POST",          
	        url: "/manager/productUpdate",        
	        data: {	
				'productNo'		: productNo,
				'productName'	: productName,
				'consumerPrice'	: consumerPrice,
				'discountRate'	: discountRate,
				'salePrice'		: salePrice,
				'stock'			: stock,
				'hotYn'			: hotYn,
				'mdPickYn'		: mdPickYn,
				'quickYn'		: quickYn, 
				'bestYn'		: bestYn,
				'saleYn'		: saleYn
			},          
	        success: function (data) { 
	        	console.log("상품수정 성공") 
	        	alert("상품정보가 수정되었습니다.")      
	        	location.href="/manager/managerMenu";   
	        },          
	        error: function (e) {  
	            console.log("상품수정 실패")      
	         }     
		});  
		
	})
});


/*관리자 메뉴 메인이미지 미리보기 설정*/
function readImage(input) {
	// 인풋 태그에 파일이 있는 경우
	if (input.files && input.files[0]) {
		// 이미지 파일인지 검사 (생략)
		// FileReader 인스턴스 생성
		const reader = new FileReader()
		// 이미지가 로드가 된 경우
		reader.onload = e => {
			const previewImage = document.getElementById('preview-image')
			previewImage.src = e.target.result
		}
		// reader가 이미지 읽도록 하기
		reader.readAsDataURL(input.files[0])
	}
}

/*관리자 메뉴 상세이미지 미리보기 설정*/
function handleImgsFilesSelect(e) {
	// 초기값 비움 (재선택시 비우기위함)
	$('#imgs_wrap').empty();
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	filesArr.forEach(function(f) {
		// 확장자 확인
		if (!f.type.match('image.*')) {
			alert('확장자는 이미지 확장자만 가능합니다');
			return;
		}
		var reader = new FileReader();
		reader.onload = function(e) {
			var img_html = '<img src=\'' + e.target.result + '\' />';
			$('#imgs_wrap').append(img_html);
		}
		reader.readAsDataURL(f);
	})
}





