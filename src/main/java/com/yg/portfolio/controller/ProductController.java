package com.yg.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductColor;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.model.ProductSize;
import com.yg.portfolio.model.Search;
import com.yg.portfolio.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/* 상품 상세정보 조회 */
	@GetMapping("/detail/{productNo}")
	public String productDetail(Model model, @PathVariable int productNo) {
		Product detailList = productService.productInfo(productNo);
		model.addAttribute("detail",detailList);
		List<ProductImg> imgDetail = productService.productImg(productNo); 
		List<ProductColor> color = productService.productColor(productNo); 
		List<ProductSize> size = productService.productSize(productNo); 
		model.addAttribute("imgDetail",imgDetail);
		model.addAttribute("color",color);
		model.addAttribute("size",size);
		return "product/productDetailForm";
	}
	
	/* 상품검색 */
	@GetMapping("/search")
	public String search(Search search, Model model
			,@RequestParam(value = "currentPage", required = false) Integer currentPage) {
		model.addAttribute("searchList",productService.search(search,currentPage,model));
		model.addAttribute("prevSearch",search.getSearch());
		return "product/search";
	}
}
