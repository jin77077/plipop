package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetail {
	private String merchant_uid;	// 가맹점 고유 주문번호
	private int productNo; 			// 상품번호
	private int quantity;			// 수량
	private String color;			// 색상	
	private String size;			// 사이즈
	private int totalPrice;			// 총 판매가
	private String category;		// 카테고리
	private String mainImage;		// 메인이미지
	private String productName;		// 상품명
	private int salePrice;			// 판매가
}

