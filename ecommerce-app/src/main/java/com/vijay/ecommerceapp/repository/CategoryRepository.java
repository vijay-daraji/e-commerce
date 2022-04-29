package com.vijay.ecommerceapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.ecommerceapp.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	CategoryEntity findByCategoryName(String categoryName);
	
	@Transactional
	void deleteByCategoryName(String categoryName);

}
