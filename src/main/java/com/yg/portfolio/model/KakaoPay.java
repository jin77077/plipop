package com.yg.portfolio.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KakaoPay {
	private String userId;				// 주문자 아이디
	private String name;				// 주문명
	private int amount;					// 결제금액
	private int delivery_price;			// 배송비
	private String delivery_name;		// 수신자명
	private String delivery_tel;		// 수신자 전화번호
	private String delivery_postcode;	// 수신자 우편번호
	private String delivery_addr;		// 수신자 주소
	private String delivery_message;;	// 배송메세지
	private String imp_uid;				// 아임포트 고유 결제번호
	private String merchant_uid;		// 가맹점 고유 주문번호
	private String paid_at;				// 결제승인시각
	private String receipt_url;			// 매출전표 url
}

