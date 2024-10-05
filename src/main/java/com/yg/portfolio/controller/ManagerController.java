package com.yg.portfolio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yg.portfolio.model.Notice;
import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.Qna;
import com.yg.portfolio.service.CommunityService;
import com.yg.portfolio.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CommunityService communityService;
	
	/* 공지사항 글 작성 Form 이동 */
	@GetMapping("/noticeForm")
	public String noticeForm(@RequestParam(value = "boardNum", required = false) Integer boardNum, Model model) {
		if (boardNum != null) {
			model.addAttribute("detail",communityService.noticeDetail(boardNum));
		}
		return "manager/noticeForm";
	}
	
	/* 공지사항 글 작성 */
	@PostMapping("/writeNotice")
	public String writeNotice(Notice notice) {
		managerService.writeNotice(notice);
		return "redirect:/community/notice";
	}
	
	/* 공지사항 글 수정 */
	@PostMapping("/updateNotice")
	public String updateNotice(Notice notice) {
		managerService.updateNotice(notice);
		return "redirect:/community/notice";
	}
	
	/* 공지사항 글 삭제 */
	@PostMapping("/deleteNotice")
	public @ResponseBody int deleteNotice(@RequestParam(value = "boardNum", required = false) Integer boardNum) {
		return managerService.deleteNotice(boardNum);
	}
	
	/* QNA 답글 페이지 이동 */
	@GetMapping("/qnaReplyForm")
	public String qnaReplyForm(@RequestParam(value = "boardNum", required = false) Integer boardNum,Model model) {
		model.addAttribute("detail",communityService.qnaDetail(boardNum));
		return "manager/qnaReplyForm";
	}
	
	/* QNA 답글 작성 */
	@PostMapping("/writeReplyQna")
	public String writeReplyQna(Qna qna) {
		managerService.writeReplyQna(qna);
		return "redirect:/community/qna";
	}
	
	/* 관리자 메뉴 */
	@GetMapping("/managerMenu")
	public String productForm(Model model) {
		List<Product> productList = managerService.productAllList();
		model.addAttribute("productList",productList);
		return "manager/managerMenu";
	}
	
	/* 상품 등록 */
	@PostMapping("/productSave")
	public String productSave(@RequestParam(value = "files[]", required = false) List files	
			, @RequestParam(value= "colorList[]", required = false) List colorList
			, @RequestParam(value= "sizeList[]", required = false) List sizeList
			, Product product) throws Exception {
			managerService.productSave(product);
			for(int i =0; i < colorList.size(); i++) { 
				managerService.productColorSave((String) colorList.get(i));
			}
			for(int i =0; i < sizeList.size(); i++) { 
				managerService.productSizeSave((String) sizeList.get(i));
			}
			for(int i =0; i < files.size(); i++) { 
				managerService.productImgSave((String) files.get(i));
			}
	    return "redirect:/";
	}
	
	/* 상품 수정 */
	@PostMapping("/productUpdate")
	@ResponseBody
	public void productUpdate(Product product) throws Exception {
		managerService.productUpdate(product);
	}
	
	/* 상품관리 목록 */
	@GetMapping("/productAllList")
	public @ResponseBody int productAllList(Model model) {
		List<Product> productList = managerService.productAllList();
		model.addAttribute("productList",productList);
		int chk =0;
		if(productList != null) {
			chk = 1;
		}
		return chk;
	}
	
}
