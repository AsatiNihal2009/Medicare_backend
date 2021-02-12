package com.medicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.medicare.entity.Category;
import com.medicare.enums.ECategory;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Boolean existsByCategoryName(ECategory categoryName);

	Category findByCategoryName(ECategory categoryName);
}
