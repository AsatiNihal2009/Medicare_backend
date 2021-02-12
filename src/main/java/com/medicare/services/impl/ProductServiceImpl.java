package com.medicare.services.impl;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.dto.request.ProductDTO;
import com.medicare.dto.response.MessageResponse;
import com.medicare.entity.Category;
import com.medicare.entity.Product;
import com.medicare.enums.Age;
import com.medicare.enums.ECategory;
import com.medicare.enums.State;
import com.medicare.repository.CategoryRepository;
import com.medicare.repository.ProductRepository;
import com.medicare.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public ResponseEntity<?> addProduct(ProductDTO productDto) {
		Product product = new Product();

		if (productRepository.existsByItemName(productDto.getItemName())) {
			return ResponseEntity.badRequest().body(new MessageResponse(productDto.getItemName() + " already exist"));
		}

		product.setItemName(productDto.getItemName());
		product.setQuantity(productDto.getQuantity());
		product.setItemTotal(productDto.getItemTotal());
		product.setAge(Age.valueOf(productDto.getAge()));
		product.setState(State.valueOf(productDto.getState()));
		product.setToggle(true);

		Category category = new Category();
		if (categoryRepository.existsByCategoryName(ECategory.valueOf(productDto.getCategoryName()))) {
			category = categoryRepository.findByCategoryName(ECategory.valueOf(productDto.getCategoryName()));
			product.setCategory(category);

			List<Product> list = new ArrayList<>();
			list.add(product);

			category.setProduct(list);

		} else {
			return ResponseEntity.badRequest().body("Undefined Category! Please specify the Correct Category");
		}
		/**
		 * Free Up Object for garbage Collection
		 */
		categoryRepository.save(category);
		category = null;
		product = null;

		return ResponseEntity.ok("Product Added successfully");
	}

	@Override
	public ResponseEntity<?> editProduct(ProductDTO productDto) {
		Product product = null;
		if (productRepository.existsByItemName(productDto.getItemName())) {

			product = productRepository.findByItemName(productDto.getItemName());
			product.setItemTotal(productDto.getItemTotal());
			product.setQuantity(productDto.getQuantity());
			productRepository.save(product);

		} else {
			addProduct(productDto);
		}

		return ResponseEntity.ok("Update Successfully");

	}

	@Override
	public ResponseEntity<?> toggleProduct(boolean toggle, String productName) {

		if (productRepository.existsByItemName(productName)) {

			Product product = productRepository.findByItemName(productName);
			product.setToggle(toggle);
			productRepository.save(product);
			return ResponseEntity.ok("Product Toggled");
		}

		return ResponseEntity.notFound().build();
	}

}
