package com.medicare.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.repository.CartRepository;
import com.medicare.repository.ProductRepository;
import com.medicare.repository.UserRepository;
import com.medicare.services.CartService;

@Service
public class CartServiceImpl implements CartService{

	private CartRepository cartRepository;
	
	private UserRepository userRepository;
	
	private ProductRepository productRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository, com.medicare.repository.UserRepository userRepository,
			ProductRepository productRepository) {
		this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	public ResponseEntity<?> addProductToCart(String productName, String username) {
		
		return null;
	}

	@Override
	public ResponseEntity<?> removeProductFromCart(String productName, String username) {
		return null;
	}
	
}
