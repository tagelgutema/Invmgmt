package com.inv.invmgmt.products.repositories;

import com.inv.invmgmt.products.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

	@Query(value= "select c  from  Category c where " +
			"c.categoryName LIKE %?1% or c.categoryDescription LIKE %?1%")
	List<Category> searchByCategory(String keyword);

}
