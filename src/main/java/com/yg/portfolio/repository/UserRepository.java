package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.User;


@Mapper
public interface UserRepository {
	// 로그인시 아이디 존재여부 조회
	public User findByUserId(String userId);
	
	// 로그인시 아이디 존재여부 조회
	public int findId(String userId);
	
	// 회원가입
	public int join (User user);
	
	// Oauth 회원가입
	public int oAuthJoin (User user);
}
