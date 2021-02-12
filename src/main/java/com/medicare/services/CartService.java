package com.medicare.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.medicare.entity.Cart;

public interface CartService {

	ResponseEntity<?> addProductToCart(String productName, String username);

	Optional<Cart> getCart(Long id);

	ResponseEntity<?> removeProductFromCart(String productName);

}
