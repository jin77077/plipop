
package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Search {
	private String search;		// 상품명
	private int minPrice;		// 최소금액
	private int maxPrice;		// 최대금액
	private int startPage;		// 시작번호
	private int endPage;		// 끝번호
	private String alignKind;	// 정렬기준
	private String category;	// 카테고리
	
	@Override
    public String toString() {
        return "Search.dto 들어온 값 ============================= \r\n"
        		+ "search: " + search + "\r\n"
        		+ "minPrice: " + minPrice + "\r\n"
        		+ "maxPrice: " + maxPrice + "\r\n"
        		+ "alignKind: "+alignKind + "\r\n"
				+ "category: "+category;
    }
	
}
