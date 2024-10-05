package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Product;

@Mapper
public interface CategoryRepository {
	
	/* NEW 카테고리 COUNT 조회 */
	int categoryNewCnt();

	/* NEW 카테고리 조회 */
	List<Product> categoryNew(int startPage, int endPage);

	/* BEST 카테고리 COUNT 조회 */
	int categoryBestCnt();
	
	/* BEST 카테고리 조회 */
	List<Product> categoryBest(int startPage, int endPage);
	
	/* 카테고리 COUNT 조회 */
	int categoryCnt(String category);

	/* OUTER 카테고리 조회 */
	List<Product> categoryOuter(int startPage, int endPage);

	/* TOP 카테고리 조회 */
	List<Product> categoryTop(int startPage, int endPage);

	/* PANTS 카테고리 조회 */
	List<Product> categoryPants(int startPage, int endPage);

	/* SHOES 카테고리 조회 */
	List<Product> categoryShoes(int startPage, int endPage);

}
