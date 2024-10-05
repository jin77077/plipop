package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	int totalCount = 0;		// 총 게시물 수
	int listCount = 8;		// 한 페이지에 보여줄 게시물 수
	int totalPage = 0;		// 총 페이지 수
	int currentPage = 0;	// 현재 페이지
	int startPage = 0;		// 시작 게시물 번호
	int endPage = 0;		// 끝 게시물 번호
	
	/* NEW 카테고리 조회 */
	public List<Product> categoryNew(Integer page, Model model) {
		totalCount = categoryRepository.categoryNewCnt(); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryNew(startPage, endPage);
	}
	
	/* BEST 카테고리 조회 */ 
	public List<Product> categoryBest(Integer page, Model model) {
		totalCount = categoryRepository.categoryBestCnt(); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryBest(startPage, endPage);
	}

	/* OUTER 카테고리 조회 */
	public List<Product> categoryOuter(Integer page, String category, Model model) {
		totalCount = categoryRepository.categoryCnt(category); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryOuter(startPage, endPage);
	}

	/* TOP 카테고리 조회 */
	public List<Product> categoryTop(Integer page, String category, Model model) {
		totalCount = categoryRepository.categoryCnt(category); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryTop(startPage, endPage);
	}

	/* PANTS 카테고리 조회 */
	public List<Product> categoryPants(Integer page, String category, Model model) {
		totalCount = categoryRepository.categoryCnt(category); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryPants(startPage, endPage);
	}

	/* SHOES 카테고리 조회 */
	public List<Product> categoryShoes(Integer page, String category, Model model) {
		totalCount = categoryRepository.categoryCnt(category); 
		paging(page); //페이징 처리
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return categoryRepository.categoryShoes(startPage, endPage);
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
