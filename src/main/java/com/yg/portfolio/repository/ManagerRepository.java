package com.yg.portfolio.repository;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.Qna;


@Mapper
public interface ManagerRepository {
	
	/* 공지사항 글 작성 */
	public int writeNotice(Notice notice);
	
	/* 공지사항 글 수정 */
	public int updateNotice(Notice notice);
	
	/* 공지사항 글 삭제 */
	public int deleteNotice(Integer boardNum);
	
	/* QNA 답글 작성 */
	public int writeReplyQna(Qna qna);

	/* 상품등록 */
	public void productSave(Product product);

	/* 이미지등록 */
	public void productImgSave(String fileName);
	
	/* 컬러등록 */
	public void productColorSave(String colorName);
	
	/* 사이즈등록 */
	public void productSizeSave(String sizeName);
	
	/* 상품목록 조회 */
	public List<Product> productAllList();

	/* 상품수정 */
	public int productUpdate(Product product);
	
}
