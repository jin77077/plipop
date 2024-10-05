package com.yg.portfolio.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.portfolio.model.User;
import com.yg.portfolio.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/* 마이페이지 */
	@GetMapping("/myPage")
	public String myPage(Model model) {
		return "members/myPage";
	}
	
	/* 회원정보 조회 */
	@GetMapping("/memberInfo")
	public String memberInfo(Model model, User user) {
		User info = memberService.memberInfo(user);
		model.addAttribute("userInfo",info);
		return "members/memberInfo";
	}
	
	/* 회원정보 수정 */
	@PostMapping("/memberModify")
	public String memberModify(User user, HttpSession session) {
		memberService.memberModify(user);
		session.removeAttribute("userName");
		session.setAttribute("userName", user.getUserName());
		return "redirect:/";
	}
	
	/* 회원탈퇴 */ 
	@GetMapping("/memberWithdrawal")
	public String memberWithdrawal(User user) {
		memberService.memberWithdrawal(user);
		return "redirect:/";
	}

}
