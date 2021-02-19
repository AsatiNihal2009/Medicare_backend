package com.medicare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.dto.request.ProductDTO;
import com.medicare.services.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/medicare/product")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@GetMapping
	public ResponseEntity<?> fetchAllProductNames(){
		return productService.fetchAllProductNames();
	}
	@GetMapping(path = "/{product}")
	public ResponseEntity<?> fetchProduct(@PathVariable(value="product")String productName){
		return productService.fetchProduct(productName);
	}
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProduct(@Validated @RequestBody ProductDTO productDto) {
		return productService.addProduct(productDto);
	}
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editProduct(@Validated @RequestBody ProductDTO productDto) {
		return productService.editProduct(productDto);
	}

	@PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> toggleProduct(@RequestParam(name = "toggle") String toggle,@RequestParam(name = "name")String name) {
		return productService.toggleProduct(Boolean.parseBoolean(toggle), name);
	}
}
