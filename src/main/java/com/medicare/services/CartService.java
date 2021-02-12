package com.medicare.services;

import org.springframework.http.ResponseEntity;

public interface CartService {

	ResponseEntity<?> addProductToCart(String productName, String username);

	ResponseEntity<?> removeProductFromCart(String productName, String username);

}
