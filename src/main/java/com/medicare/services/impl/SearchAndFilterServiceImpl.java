package com.medicare.services.impl;

import java.util.List;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medicare.dto.request.ProductDTO;
import com.medicare.entity.Product;
import com.medicare.enums.Age;
import com.medicare.enums.ECategory;
import com.medicare.enums.State;
import com.medicare.repository.CategoryRepository;
import com.medicare.repository.ProductRepository;
import com.medicare.services.SearchAndFilterService;

import java.util.stream.Collectors;
@Service
public class SearchAndFilterServiceImpl implements SearchAndFilterService{

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	Function<Product,ProductDTO> mapper=d -> new ProductDTO(d.getCategory().getCategoryName().name(),d.getAge().name(),d.getState().name(),d.getQuantity(),d.getItemName(),d.getItemTotal());

	@Autowired
	public SearchAndFilterServiceImpl(ProductRepository productRepository,CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository=categoryRepository;
	}

	@Override
	public ResponseEntity<List<ProductDTO>> searchFilterAndSortProduct(String productName,String age, String state,String category,int quantity) {
		return ResponseEntity.ok(
				productRepository.findAllByItemNameOrAgeOrState(productName,Age.valueOf(age), State.valueOf(state))
				.stream()
				.filter(e->e.getCategory().equals(categoryRepository.findByCategoryName(ECategory.valueOf(category))))
				.filter(e-> e.getQuantity()>quantity)
				.sorted()
				.map(mapper)
				.collect(Collectors.toList())
				);
	}
	
	
	
	
	
	
}
