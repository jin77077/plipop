package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductImg {
	private int imageNo;
	private int productNo;
	private String fileName;
}
