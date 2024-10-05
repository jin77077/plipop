package com.yg.portfolio.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yg.portfolio.model.PrincipalDetails;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인 폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		model.addAttribute("error", error);
		return "users/loginForm";
	}

	// 로그인 성공 메소드
	// 로그인 성공시 세션에 저장되어 있는 User값은 2가지 방법으로 가져온다.
	// 1. Authentication을 통해 principalDetails로 다운캐스팅하여 가져오는 방법
	// 2. @AuthenticationPrincipal 어노테이션을 통해 바로 PrincipalDetails에서 User를 가져오는 방법
	@GetMapping("/loginSucess")
	public String loginSucess(@AuthenticationPrincipal PrincipalDetails principalDetailss, HttpSession session) {
		session.setAttribute("userRole", principalDetailss.getUser().getRole());
		session.setAttribute("userId", principalDetailss.getUser().getUserId());
		session.setAttribute("userName", principalDetailss.getUser().getUserName());
		return "redirect:/";
	}

	// 회원가입 폼
	@GetMapping("/joinForm")
	public String joinForm(@RequestParam(value = "nomalJoin", required = false) String nomalJoin, Model model) {
		if (nomalJoin != null) {
			model.addAttribute("nomalJoin", "Y");
		}
		return "users/joinForm";
	}

	// 회원가입 => 아이디 중복체크
	@GetMapping("/findId")
	public @ResponseBody int findId(@RequestParam(value = "userId", required = false) String userId) {
		int cnt = userService.findId(userId);
		if (cnt >= 1) {
			return 1;
		} else {
			return 0;
		}
	}

	// 회원가입
	@PostMapping("/join")
	public String join(User user) {
		userService.join(user);
		return "redirect:/users/loginForm";
	}
}
