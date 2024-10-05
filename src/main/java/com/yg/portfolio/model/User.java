package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	private String userId;			// 유저아이디
	private String userName; 		// 회원 이름
	private String password;		// 비밀번호
	private String email; 			// 이메일
	private String emailProvider; 	// 이메일 제공자
	private String phone; 			// 전화번호
	private String gender;			// 성별
	private String emailReceiveYn;	// 이메일 수신여부
	private String smsReceiveYn;	// 문자 수신여부
	private String zipCode;			// 우편번호
	private String address;			// 기본주소
	private String detailAddress;	// 상세주소
	private String role; 			// 권한 이름
	private String provider;		// provider
	private String providerId;		// provider Id
	private String birthYear;		// 생년
	private String birthMonth;		// 월
	private String birthDay;		// 일
	
}
