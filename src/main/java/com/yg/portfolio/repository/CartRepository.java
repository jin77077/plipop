package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.yg.portfolio.model.Cart;

@Mapper
public interface CartRepository {

	/* 장바구니 목록 */
	List<Cart> cartList(String userId);

	/* 장바구니 저장 */
	void cartSave(Cart saveList);

	/* 장바구니 삭제 */
	void cartDelete(String cartNo);

	/* 장바구니 수정 */
	void cartUpdate(String cartNo, int quantity);

	
}
