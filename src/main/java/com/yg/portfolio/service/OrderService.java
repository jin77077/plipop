package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.OrderDetail;
import com.yg.portfolio.model.OrderForm;
import com.yg.portfolio.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/* 주문서 상세조회 */
	public OrderForm orderDetail(int cartNo) {
		return orderRepository.orderDetail(cartNo);
	}

	/* 주문 등록 */
	public void orderSave(KakaoPay kakaopay) {
		orderRepository.orderSave(kakaopay);
	}

	/* 주문내역 조회 */
	public List<KakaoPay> orderList(String userId) {
		return orderRepository.orderList(userId);
	}

	/* 주문확인 */
	public KakaoPay checkPayment(String merchant_uid) {
		return orderRepository.checkPayment(merchant_uid);
		
	}
	
	/* 주문상세정보 저장 */
	public void orderDetailSave(OrderDetail detail) {
		 orderRepository.orderDetailSave(detail);
		
	}

	/* 주문상세정보 조회 */
	public List<OrderDetail> productDetails(String merchant_uid) {
		return orderRepository.productDetails(merchant_uid);
	}
}
