package com.medicare.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {

	private String category;
	
	private String age;
	
	private String state;
	
	private int quantity;
	
	private String productName;
	
	private double itemTotal;
}
