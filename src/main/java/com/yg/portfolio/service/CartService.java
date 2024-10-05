package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yg.portfolio.model.Cart;
import com.yg.portfolio.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	/* 장바구니 목록 */
	public List<Cart> cartList(String userId) {
		return cartRepository.cartList(userId);
	}
	
	/* 장바구니 저장 */
	public void cartSave(Cart list) {
		cartRepository.cartSave(list);
	}
	
	/* 장바구니 삭제 */
	public void cartDelete(String cartNo) {
		cartRepository.cartDelete(cartNo);
	}
	
	/* 장바구니 수정 */
	public void cartUpdate(String cartNo, int quantity) {
		cartRepository.cartUpdate(cartNo, quantity);
	}

	
}
