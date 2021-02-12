package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.services.CartService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medicare/cart")
@PreAuthorize("hasRole('USER')")
public class CartController {
	
	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProductToCart(@RequestParam(name = "productName")String productName,@RequestParam(name = "username")String username){
		return cartService.addProductToCart(productName,username);
	}
	
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeProductFromCart(@RequestParam(name = "productName")String productName,@RequestParam(name = "username")String username){
		return cartService.removeProductFromCart(productName,username);
	}

	
}

	