// 최종 가격	
var totalPrice = [];
var totalQuantity = [];
var count = 0;

$(function() {
	/*가격 천단위 구분기호 (정규식 사용)*/
	var consumerPrice = priceToString($('#consumerPrice').html());
	var salePrice = priceToString($('#salePrice').html());
	$('#consumerPrice').html(consumerPrice);
	$('#salePrice').html(salePrice);

	// 색상변경시 사이즈 초기화 
	$('#select-color').change(function() {
		$('#select-size').val('*').prop("selected", true);
	});

	// 색상,사이즈 선택 시 최종상품,가격 추가
	$('#select-size').change(function() {
		// 상품 가격
		var salePrice = Number($('#salePrice').text().replace('￦', '').replace('원', '').replace(',', ''));
		var productName = $('#productName').text();
		var color = $('#select-color').val();
		var size = $('#select-size').val();
		var lastIndex = $('.total-product-tr:last').index();
		if ($('#select-color').val() != '*' && $('#select-size').val() != '*') {
			var insertColor = $('#select-color').val();
			var inserSize = $('#select-size').val();
			for (var i = 0; i <= lastIndex; i++) {
				if ($('.colorText').eq(i).text() == insertColor && $('.sizeText').eq(i).text() == inserSize) {
					alert('이미 등록된 상품입니다.')
					$('#select-size').val('*').prop("selected", true);
					return;
				}
			}
			var html = '<tr class="total-product-tr">\
							<td class="totalTd1"><input type="hidden" name="orderFormList['+count+'].productName" value="'+productName+'"><b class="productNameText">'+ productName + '</b>\
							<input type="hidden" name="orderFormList['+count+'].color" value="'+color+'"><br>-<span class="colorText">' + color + '</span>\
							<input type="hidden" name="orderFormList['+count+'].size" value="'+size+'">/ <span class="sizeText">' + size + '</span></td>\
							<td class="totalTd2">\
								<span class="quantityArea">\
									<input type="number" id="quantity" name="orderFormList['+count+'].quantity" class="quantity" value="1" readOnly>\
									<a class="upBtn quantityBtn">\
										<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif" alt="수량증가">\
									</a>\
									<a class="downBtn quantityBtn">\
											<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif" id="option_box1_down" class="option_box_down" alt="수량감소">\
									</a>\
								</span>\
								<a class="delete">\
									<img src="//img.echosting.cafe24.com/design/skin/default/product/btn_price_delete.gif" alt="삭제" class="deleteBtn">\
								</a>\
							</td>\
							<td class="totalTd3"><b class="salePriceText">￦'+ priceToString(salePrice) + '원</b></td>\
						</tr>';
			$('.total-table').append(html);
			totalPrice.push(salePrice);
			totalPriceSet(totalPrice);
			totalQuantity.push(Number($('.quantity').val()));
			totalQuantitySet(totalQuantity);
			$('#select-color').val('*').prop("selected", true);
			$('#select-size').val('*').prop("selected", true);
			count++;
		}
	});


	// totalProduct 수량증가
	$(document).on('click', '.upBtn', function() {
		// 상품 가격
		var salePrice = Number($('#salePrice').text().replace('￦', '').replace('원', '').replace(',', ''));
		var index = $('.upBtn').index(this);
		var quantity = parseInt($('.quantity').eq(index).val());
		$('.quantity').eq(index).val(quantity + 1);
		totalPrice[index] = salePrice * (quantity + 1);
		totalQuantity[index] = Number($('.quantity').eq(index).val());
		$('.salePriceText').eq(index).text('￦' + priceToString(totalPrice[index]) + '원');
		totalPriceSet(totalPrice);
		totalQuantitySet(totalQuantity);
	});

	// totalProduct 수량감소
	$(document).on('click', '.downBtn', function() {
		// 상품 가격
		var salePrice = Number($('#salePrice').text().replace('￦', '').replace('원', '').replace(',', ''));
		var index = $('.downBtn').index(this);
		var quantity = parseInt($('.quantity').eq(index).val());
		if (quantity > 1) {
			$('.quantity').eq(index).val(quantity - 1)
			totalPrice[index] = salePrice * (quantity - 1);
			totalQuantity[index] = Number($('.quantity').eq(index).val());
			$('.salePriceText').eq(index).text('￦' + priceToString(totalPrice[index]) + '원');
			totalPriceSet(totalPrice)
			totalQuantitySet(totalQuantity)
		}
		else {
			alert('최소 주문수량은 1개 입니다.');
		}
	});

	// totalProduct 삭제버튼
	$(document).on('click', '.deleteBtn', function() {
		var index = $('.deleteBtn').index(this)
		$('.total-product-tr').eq(index).remove();
		totalPrice.splice(index, 1);
		totalQuantity.splice(index, 1)
		totalPriceSet(totalPrice)
		totalQuantitySet(totalQuantity)
	});

	var productList = [];

	// 장바구니 저장버튼
	$('#cartBtn').click(function() {
		var sessionId = $('#userId').val(); 
		if(isEmpty(sessionId)){
			alert("로그인 후 이용가능합니다.")
			document.location.href="/users/loginForm";
			return;
		}
		productCnt = $('.total-product-tr:last').index();
		if (productCnt >= 0) {
			var chk = confirm("장바구니에 담으시겠습니까?");
			if (chk) {
				var lastIndex = $('.total-product-tr:last').index();
				for (var i = 0; i <= lastIndex; i++) {
					var data = {
						productNo: $('.productNo').val(),
						color: $('.totalTd1 > span.colorText').eq(i).text(),
						size: $('.totalTd1 > span.sizeText').eq(i).text(),
						quantity: totalQuantity[i],
						userId : sessionId
					};
					productList.push(data);
				}
				$.ajax({
					type: "POST",
					url: "/cart/cartSave",
					data: {
						cartList: JSON.stringify(productList)
					},
					success: function(res) {
						console.log("장바구니 저장");
						location.href="/cart/cartList?userId="+sessionId;
					},
					error: function(request, status, error) {
						console.log("장바구니 저장실패");
					}
				})
			}
			else {
				return;
			}
		} 
		else {
			alert("담을 상품이 없습니다.")
		}
	});
	
	// 바로구매 버튼
	$('#buyBtn').click(function(){
		var sessionId = $('#userId').val(); 
		if(isEmpty(sessionId)){
			alert("로그인 후 이용가능합니다.")
			document.location.href="/users/loginForm";
			return;
		}
		productCnt = $('.total-product-tr:last').index();
		if (productCnt >= 0) {
			var chk = confirm("바로구매 하시겠습니까?");
			if(chk){
				$('#productDetailForm').submit();
			}
			else {
				retrun;
			}
		}
		else {
			alert("담을 상품이 없습니다.")
		}
	})

});

// 최종가격 입력 함수
function totalPriceSet(totalPrice) {
	var index = $('.total-product-tr:last').index();
	var sum = 0;
	for (var i = 0; i <= index; i++) {
		sum += totalPrice[i];
	}
	$('#totalPrice').text(priceToString(sum));
}

// 최종수량 입력 함수
function totalQuantitySet(totalQuantity) {
	var index = $('.total-product-tr:last').index();
	var sumQuantity = 0;
	for (var i = 0; i <= index; i++) {
		sumQuantity += totalQuantity[i]
	}
	$('#quantityText').text(sumQuantity);
}
