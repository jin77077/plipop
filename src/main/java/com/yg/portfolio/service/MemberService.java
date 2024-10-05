package com.yg.portfolio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.yg.portfolio.model.User;
import com.yg.portfolio.repository.MemberRepository;


@Service
public class MemberService{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MemberRepository memberRepository;

	/* 회원정보 조회 */
	public User memberInfo(User user) {
		return memberRepository.memberInfo(user);
	}
	
	/* 회원정보 수정 */
	public void memberModify(User user) {
		// BCryptPasswordEncoder 통해 비밀번호 암호화
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		memberRepository.memberModify(user);
	}
	
	/* 회원탈퇴 */
	public void memberWithdrawal(User user) {
		memberRepository.memberWithdrawal(user);
	}
	

}
