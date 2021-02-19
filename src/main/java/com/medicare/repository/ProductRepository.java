package com.medicare.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medicare.entity.Product;
import com.medicare.enums.Age;
import com.medicare.enums.State;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>{

	Boolean existsByItemName(String itemName);

	Product findByItemName(String itemName);
		
	List<Product> findAllByItemNameOrAgeOrState(String itemName,Age age,State state);
	
	@Modifying
	@Query(value = "update product set cart_user_id=NULL where item_name=?1",nativeQuery = true)
	void removeProductFromCart(String productName);
	
	@Query(value = "select item_name from product",nativeQuery = true)
	List<String> findAllProductName();
}
