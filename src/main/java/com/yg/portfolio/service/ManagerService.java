package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	/* 공지사항 글 작성 */
	public void writeNotice(Notice notice) {
		managerRepository.writeNotice(notice);
	};
	
	/* 공지사항 글 수정 */
	public void updateNotice(Notice notice) {
		managerRepository.updateNotice(notice);
	}
	
	/* 공지사항 글 삭제 */
	public int deleteNotice(Integer boardNum) {
		return managerRepository.deleteNotice(boardNum);
	}
	
	/* QNA 답글 작성 */
	public int writeReplyQna(Qna qna) {
		qna.setTitle("'플리팝'에서 답변드립니다");
		qna.setChkSecret("secret");
		return managerRepository.writeReplyQna(qna);
	}

	/* 상품등록 */
	public void productSave(Product product) {
		managerRepository.productSave(product);
	}

	/* 상세이미지 등록 */
	public void productImgSave(String fileName) {
		managerRepository.productImgSave(fileName);
	}

	/* 컬러등록 */
	public void productColorSave(String colorName) {
		managerRepository.productColorSave(colorName);
	};
	
	/* 사이즈등록 */
	public void productSizeSave(String sizeName) {
		managerRepository.productSizeSave(sizeName);
	}

	/* 상품목록 조회 */
	public List<Product> productAllList() {
		return managerRepository.productAllList();
	}
	
	/* 상품수정 */
	public int productUpdate(Product product) {
		return managerRepository.productUpdate(product);
	};
	
	
}
