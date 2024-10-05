package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.OrderDetail;
import com.yg.portfolio.model.OrderForm;

@Mapper
public interface OrderRepository {
	
	/* 주문서 상세조회 */
	OrderForm orderDetail(int cartNo);

	/* 주문 등록 */
	void orderSave(KakaoPay kakaopay);
	
	/* 주문내역 조회 */
	List<KakaoPay> orderList(String userId);

	/* 주문확인 */
	KakaoPay checkPayment(String merchant_uid);
	
	/* 주문상세정보 저장 */
	void orderDetailSave(OrderDetail detail);
	
	/* 주문상세정보 조회 */
	List<OrderDetail> productDetails(String merchant_uid);
	
}
