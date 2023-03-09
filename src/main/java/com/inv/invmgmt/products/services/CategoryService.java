package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

	List<Category> getAll();
	List<Category> searchByCategory(String keyword);
	void saveCategory(Category category);
	void addNewCategory(Category category);
	Category searchCategoryById(Integer id);
	void deleteUsingId(Integer id);
	Category updateCategory(Category category);
	Page<Category> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}