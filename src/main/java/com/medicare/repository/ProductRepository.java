package com.medicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicare.entity.Product;
import com.medicare.enums.Age;
import com.medicare.enums.State;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Boolean existsByItemName(String itemName);

	Product findByItemName(String itemName);
	
	List<Product> findAllByItemName(String itemName);
	
	List<Product> findAllByItemNameOrAgeOrState(String itemName,Age age,State state);
}
