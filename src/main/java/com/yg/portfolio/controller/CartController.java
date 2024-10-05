package com.yg.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yg.portfolio.model.Cart;
import com.yg.portfolio.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	/* 장바구니 목록 */
	@GetMapping("/cartList")
	public String cartList(String userId, Model model) {
		List<Cart> cartList = cartService.cartList(userId);
		model.addAttribute("cartList",cartList);
		return "cart/cart";
	}
	
	/* 장바구니 등록 */
	@PostMapping("/cartSave")
	@ResponseBody
	public void cartSave(@RequestParam(value="cartList") String cartlist) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		// JSON ARRAY로 변환
		JSONArray jsonList = (JSONArray)jsonParser.parse(cartlist);
		ArrayList<Cart> saveList = new ArrayList<Cart>();
		for (Object object : jsonList) {
			// JSON ARRAY에 들어있는 오브젝트를 JSON OBJECT로 변환
			JSONObject jsonObject = (JSONObject) object;
			// CART 에 저장
			Cart testCart = new Cart();
			testCart.setProductNo(Integer.parseInt(String.valueOf(jsonObject.get("productNo"))));
			testCart.setSize((String)jsonObject.get("size"));
			testCart.setColor((String)jsonObject.get("color"));
			testCart.setQuantity(Integer.parseInt(String.valueOf(jsonObject.get("quantity"))));
			testCart.setUserId((String)jsonObject.get("userId"));
			// saveList에 저장
			saveList.add(testCart);
		}
		 
		for (Cart list : saveList) {
			// 장바구니 저장
			cartService.cartSave(list);
		}
	}
	
	/* 장바구니 상품삭제 */
	@PostMapping("/cartDelete")
	@ResponseBody
	public void cartDelete(@RequestParam(value="cartNo") String cartNo) {
		cartService.cartDelete(cartNo);
	}
	
	/* 장바구니 수량수정 */
	@PostMapping("/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam(value="cartNo") String cartNo, @RequestParam(value="quantity") int quantity) {
		cartService.cartUpdate(cartNo, quantity);
	}
}
