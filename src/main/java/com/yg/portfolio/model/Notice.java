package com.yg.portfolio.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {
	private String userId;			// 유저아이디
	private String userName; 		// 작성자
	private String title;			// 제목
	private String content;			// 내용
	private Timestamp writeDate;	// 작성일
	private int boardNum;			// 번호
	private int count;				// 조회수
	private int totalCnt;			// 게시물 총개수
}
