package com.medicare.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.entity.Cart;
import com.medicare.entity.Product;
import com.medicare.entity.User;
import com.medicare.repository.CartRepository;
import com.medicare.repository.ProductRepository;
import com.medicare.repository.UserRepository;
import com.medicare.services.CartService;

@Service
public class CartServiceImpl implements CartService {

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
		Product product;
		Cart cart;
		if (!cartRepository.existsByProductItemName(productName)) {
			User user = userRepository.findByUsername(username).get();
			product = productRepository.findByItemName(productName);
			if (cartRepository.existsByUserID(user.getID())) {
				cart = cartRepository.findByUserID(user.getID());
			} else {
				cart = new Cart();
				cart.setUser(user);
			}
			product.setCart(cart);
			cart.setPurchasedDate(new Date().getTime());
			cart.setPrice(cart.getPrice() + product.getItemTotal());

			List<Product> list = new ArrayList<>();
			list.add(product);

			cart.setProduct(list);
			cartRepository.save(cart);
			return ResponseEntity.ok().build();
		} else
			return new ResponseEntity<>("Already exist", HttpStatus.CONFLICT);
	}

	@Override
	public Optional<Cart> getCart(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public ResponseEntity<?> removeProductFromCart(String productName) {
		productRepository.removeProductFromCart(productName);
		return ResponseEntity.ok().build();
	}

}
