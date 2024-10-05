package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductColor;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.model.ProductSize;
import com.yg.portfolio.model.Search;


@Mapper
public interface ProductRepository {
	
	/* BEST 카테고리 조회 */
	public List<Product> productBest();
	
	/* NEW 카테고리 조회 */
	public List<Product> productNew();

	/* OUTER 카테고리 조회 */
	public List<Product> productOuter();

	/* TOP 카테고리 조회 */
	public List<Product> productTop();

	/* PANTS 카테고리 조회 */
	public List<Product> productPants();
	
	/* 상품 상세정보 조회 */
	public Product productInfo(int productNo);

	/* 상품 상세이미지 조회 */
	public List<ProductImg> productImg(int productNo);

	/* 상품 색상조회 */
	public List<ProductColor> productColor(int productNo);

	/* 상품 사이즈 조회 */
	public List<ProductSize> productSize(int productNo);

	/* 상품 검색 count 조회 */
	public int searchCnt(Search search);
	
	/* 상품 검색 */
	public List<Product> search(Search search);
	 	
}
