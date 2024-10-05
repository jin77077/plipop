package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductColor;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.model.ProductSize;
import com.yg.portfolio.model.Search;
import com.yg.portfolio.repository.CommunityRepository;
import com.yg.portfolio.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	int totalCount = 0;		// 총 게시물 수
	int listCount = 8;		// 한 페이지에 보여줄 게시물 수
	int totalPage = 0;		// 총 페이지 수
	int currentPage = 0;	// 현재 페이지
	int startPage = 0;		// 시작 게시물 번호
	int endPage = 0;		// 끝 게시물 번호
	
	/* BEST 카테고리 조회 */
	public List<Product> productBest() {
		return productRepository.productBest();
	}
	
	/* NEW 카테고리 조회 */
	public List<Product> productNew() {
		return productRepository.productNew();
	}
	
	/* OUTER 카테고리 조회 */
	public List<Product> productOuter() {
		return productRepository.productOuter();
	}

	/* TOP 카테고리 조회 */
	public List<Product> productTop() {
		return productRepository.productTop();
	}

	/* PANTS 카테고리 조회 */
	public List<Product> productPants() {
		return productRepository.productPants();
	}
	
	/* 상품 상세정보 조회 */
	public Product productInfo(int productNo) {
		return productRepository.productInfo(productNo);
	}
	
	/* 상품 상세이미지 조회 */
	public List<ProductImg> productImg(int productNo) {
		return productRepository.productImg(productNo);
	}
	
	/* 상품 색상조회 */
	public List<ProductColor> productColor(int productNo) {
		return productRepository.productColor(productNo);
	}

	/* 상품 사이즈 조회 */
	public List<ProductSize> productSize(int productNo) {
		return productRepository.productSize(productNo);
	}
	
	/* 상품 검색 */
	public List<Product> search(Search search, Integer page, Model model) {
		totalCount = productRepository.searchCnt(search);
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		search.setStartPage(startPage);
		search.setEndPage(endPage);
		List<Product> list = productRepository.search(search);
        return productRepository.search(search);
	}
	
	/* 페이징 */
	public void paging(Integer page) {
		totalPage = totalCount / listCount + (totalCount % listCount > 0 ? 1 : 0);
		if (page == null) {
			/* page값 없을경우 1로 설정 */
			currentPage = 1;
		}
		else {
			if (page > totalPage) {
				currentPage = totalPage;
			}
			else if (page < 1) {
				currentPage = 1;
			}
			else {
				currentPage = page;
			}
		}
		startPage = (listCount * (currentPage - 1)) + 1;
		endPage = currentPage * listCount;
	}
	
}
