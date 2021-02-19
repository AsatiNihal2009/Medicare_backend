package com.medicare.services;

import org.springframework.http.ResponseEntity;

import com.medicare.dto.request.ProductDTO;

public interface ProductService {

	ResponseEntity<?> addProduct(ProductDTO productDto);

	ResponseEntity<?> editProduct(ProductDTO productDto);

	ResponseEntity<?> toggleProduct(boolean toggle, String productName);

	ResponseEntity<?> fetchProduct(String productName);

	ResponseEntity<?> fetchAllProductNames();

}
