package com.yg.portfolio.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.OrderDetail;
import com.yg.portfolio.model.OrderForm;
import com.yg.portfolio.model.OrderFormList;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.CartService;
import com.yg.portfolio.service.MemberService;
import com.yg.portfolio.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CartService cartService;

	/* 주문내역 */
	@GetMapping("/orderList")
	public String orderList(User user, Model model, HttpSession session) {
		List<KakaoPay> orderList = orderService.orderList((String) session.getAttribute("userId"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		for (KakaoPay kakaoPay : orderList) {
			Date date = new Date(Long.parseLong(kakaoPay.getPaid_at())*1000L);
			kakaoPay.setPaid_at(format.format(date));
		}
		model.addAttribute("orderList",orderList);
		return "order/orderList";
	}
	
	/* 주문서 상세정보 */
	@GetMapping("/orderDetail")
	public String orderFormCart(OrderFormList list, Model model, HttpSession session){
			System.out.println("list값 : "+list);
			List<OrderForm> orderList = new ArrayList<OrderForm>();
			// 장바구니에서 가져온 CartNo 리스트
			for (OrderForm order : list.getOrderFormList()) {
				if(order.getCheck() != null) { // checkBox가 체크되었을 경우
					int cartNo = order.getCartNo();
					orderList.add(orderService.orderDetail(cartNo)); // cart에 담긴 정보 가져와서 orderList에 추가
				}
			}
			User user = new User();
			user.setUserId((String) session.getAttribute("userId"));
			model.addAttribute("memberInfo",memberService.memberInfo(user));
			model.addAttribute("orderList",orderList);
		return "order/orderForm";
	}

	/* 주문서 상세정보 */
	@GetMapping("/directOrderDetail")
	public String orderFormBuy(OrderFormList list, Model model, HttpSession session
			, @RequestParam(value="productNo") int productNo
			, @RequestParam(value="category") String category
			, @RequestParam(value="mainImage") String mainImage
			, @RequestParam(value="salePrice") int salePrice) throws ParseException {
			List<OrderForm> orderList = new ArrayList<OrderForm>();
			// 장바구니에서 가져온 CartNo 리스트
			for (OrderForm order : list.getOrderFormList()) {
				OrderForm saveList = new OrderForm();
				saveList.setProductNo(productNo);
				saveList.setProductName(order.getProductName());
				saveList.setSize(order.getSize());
				saveList.setColor(order.getColor());
				saveList.setQuantity(order.getQuantity());
				saveList.setCategory(category);
				saveList.setMainImage(mainImage);
				saveList.setSalePrice(salePrice);
				saveList.setTotalPrice(salePrice*order.getQuantity());
				orderList.add(saveList);
			}
			User user = new User();
			user.setUserId((String) session.getAttribute("userId"));
			model.addAttribute("memberInfo",memberService.memberInfo(user));
			model.addAttribute("orderList",orderList);
		return "order/orderForm";
	}

	/* 카카오페이 결제 */
	@PostMapping("/kakaoPayment")
	public @ResponseBody String kakaoPayment(KakaoPay kakaopay
			, @RequestParam("cartList") List<String> cartList
			, @RequestParam("productList") String productList) throws ParseException {
		// 결제정보 등록
		orderService.orderSave(kakaopay);
		
		// 장바구니 목록 삭제
		if(cartList !=null) {
			for (String cartNo : cartList) {
			cartService.cartDelete(cartNo);
			}
		}
		
		if(productList !=null) {
			JSONParser jsonParser = new JSONParser();
			// JSON ARRAY로 변환
			JSONArray jsonList = (JSONArray)jsonParser.parse(productList);
			for (Object object : jsonList) {
				// JSON ARRAY에 들어있는 오브젝트를 JSON OBJECT로 변환
				JSONObject jsonObject = (JSONObject) object;
				// CART 에 저장
				OrderDetail detail = new OrderDetail();
				detail.setMerchant_uid(kakaopay.getMerchant_uid());
				detail.setProductNo(Integer.parseInt(String.valueOf(jsonObject.get("productNo"))));
				detail.setSize((String)jsonObject.get("size"));
				detail.setColor((String)jsonObject.get("color"));
				detail.setQuantity(Integer.parseInt(String.valueOf(jsonObject.get("quantity"))));
				detail.setTotalPrice(Integer.parseInt(String.valueOf(jsonObject.get("totalPrice"))));
				orderService.orderDetailSave(detail);
			}
		}
		return kakaopay.getMerchant_uid();
	}
	
	// 주문확인
	@GetMapping("/checkPayment")
	public String checkPayment(@RequestParam(value="merchant_uid") String merchant_uid,Model model) {
		KakaoPay paymentInfo = orderService.checkPayment(merchant_uid);
		List<OrderDetail> productList = orderService.productDetails(merchant_uid);
		Date date = new Date(Long.parseLong(paymentInfo.getPaid_at())*1000L);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		paymentInfo.setPaid_at(format.format(date));
		model.addAttribute("payment",paymentInfo);
		model.addAttribute("productList",productList);
		return "order/checkPayment";
	}
	
	// 주문내역 상세정보
	@GetMapping("/orderDetailList")
	public @ResponseBody List<OrderDetail> orderDetailList(@RequestParam(value="merchant_uid") String merchant_uid) {
		List<OrderDetail> productList = orderService.productDetails(merchant_uid);
		return productList;
	}
}
