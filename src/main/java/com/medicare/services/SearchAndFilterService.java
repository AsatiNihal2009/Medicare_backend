package com.medicare.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medicare.dto.request.ProductDTO;

public interface SearchAndFilterService {

	ResponseEntity<List<ProductDTO>> searchFilterAndSortProduct(String productName, String age, String state,
			String category, int quantity);

}
