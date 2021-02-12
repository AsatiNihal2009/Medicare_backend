package com.medicare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicare.dto.request.ProductDTO;
import com.medicare.services.SearchAndFilterService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/medicare/user")
@PreAuthorize("hasRole('USER')")
public class SearchAndFilterController {

	private SearchAndFilterService searchAndFilterService;

	@Autowired
	public SearchAndFilterController(SearchAndFilterService searchAndFilterService) {
		this.searchAndFilterService = searchAndFilterService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam(name = "product") String productName,
			@RequestParam(name = "age") String age, @RequestParam(name = "state") String state,
			@RequestParam(name = "category") String category,
			@RequestParam(name = "quantity") String quantity) {
		return searchAndFilterService.searchFilterAndSortProduct(productName, age, state, category, Integer.valueOf(quantity));
	}
}
