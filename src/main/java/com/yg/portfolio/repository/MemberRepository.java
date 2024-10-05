package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.User;


@Mapper
public interface MemberRepository {
	
	/* 회원정보 조회 */
	public User memberInfo(User user);

	/* 회원정보 수정 */
	public void memberModify(User user);
	
	/* 회원탈퇴 */
	public void memberWithdrawal(User user);
}
