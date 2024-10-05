package com.yg.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.repository.CommunityRepository;

@Service
public class CommunityService {

	@Autowired
	private CommunityRepository communityRepository;
	
	int totalCount = 0;		// 총 게시물 수
	int listCount = 10;		// 한 페이지에 보여줄 게시물 수
	int totalPage = 0;		// 총 페이지 수
	int currentPage = 0;	// 현재 페이지
	int startPage = 0;		// 시작 게시물 번호
	int endPage = 0;		// 끝 게시물 번호
	
	/* 공지사항 조회 */
	public List<Notice> notice(Integer page, Model model) {
		totalCount = communityRepository.noticeCnt(); // 공지사항 총 게시물 수 
		paging(page); //페이징 처리
		List<Notice> noticeList = communityRepository.notice(startPage, endPage);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return noticeList;
	}
	
	/* 공지사항 검색 */
	public List<Notice> noticeSearch(Integer page, Model model, String search, String searchKind) {
		totalCount = communityRepository.noticeSearchCnt(search, searchKind); // 공지사항 검색된 게시물 수
		paging(page); //페이징 처리
		List<Notice> noticeSearchList = communityRepository.noticeSearch(startPage, endPage, search, searchKind);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", search);
		model.addAttribute("searchKind", searchKind);
		return noticeSearchList;
	}
	
	/* 공지사항 상세조회 */
	public Notice noticeDetail(int boardNum) {
		communityRepository.noticeCntUp(boardNum);
		return communityRepository.noticeDetail(boardNum);
	}
	
	/* 페이징 */
	public void paging(Integer page) {
		totalPage = totalCount / listCount + (totalCount % listCount > 0 ? 1 : 0);
		if (page == null) {
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
	
	
	/* QNA 조회 */
	public List<Qna> qna(Integer page, Model model) {
		totalCount = communityRepository.qnaCnt(); // 공지사항 총 게시물 수 
		paging(page); //페이징 처리
		List<Qna> qnaList = communityRepository.qna(startPage, endPage);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		return qnaList;
	}
	
	/* QNA 검색 */
	public List<Notice> qnaSearch(Integer page, Model model, String search, String searchKind) {
		totalCount = communityRepository.qnaSearchCnt(search, searchKind); // 공지사항 검색된 게시물 수
		paging(page); //페이징 처리
		List<Notice> qnaSearchList = communityRepository.qnaSearch(startPage, endPage, search, searchKind);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("search", search);
		model.addAttribute("searchKind", searchKind);
		return qnaSearchList;
	};
	
	/* QNA 상세조회 */
	public Qna qnaDetail(int boardNum) {
		communityRepository.qnaCntUp(boardNum);
		return communityRepository.qnaDetail(boardNum);
	}
	
	/* QNA 글 작성 */
	public void writeQna(Qna qna) {
		communityRepository.writeQna(qna);
	}
	
	/* QNA 글 수정 */
	public void updateQna(Qna qna) {
		communityRepository.updateQna(qna);
	}
	
	/* QNA 글 삭제 */
	public int deleteQna(Integer boardNum) {
		return communityRepository.deleteQna(boardNum); 
	}
	
}
