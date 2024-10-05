package com.yg.portfolio.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

// 시큐리티 로그인실패 핸들러
@Service
public class AuthFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException accessException) throws IOException, ServletException {
		String errorMsg = null;
		if (accessException instanceof UsernameNotFoundException) {
			errorMsg = "존재하지 않는 아이디 입니다.";

		} else if (accessException instanceof BadCredentialsException) {
			errorMsg = "비밀번호를 확인해주세요";

		} else if (accessException instanceof LockedException) {
			errorMsg = "잠긴 계정입니다.";

		} else if (accessException instanceof DisabledException) {
			errorMsg = "비활성화된 계정입니다.";

		} else if (accessException instanceof AccountExpiredException) {
			errorMsg = "만료된 계정입니다.";

		} else if (accessException instanceof CredentialsExpiredException) {
			errorMsg = "비밀번호가 만료되었습니다.";
		}
		RequestDispatcher view = request.getRequestDispatcher("/users/loginForm?error=" + errorMsg);
		view.forward(request, response);

	}

}
