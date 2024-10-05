package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductColor {
	private int colorNo;
	private int productNo;
	private String colorName;
}
