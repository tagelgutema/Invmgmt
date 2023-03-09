package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.Category;
import com.inv.invmgmt.products.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


	@Autowired
	private CategoryRepository categoryRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void addNewCategory(Category category) {
		categoryRepository.save(category);

	}
	@Override
	public List<Category> searchByCategory(String keyword) {
		return categoryRepository.searchByCategory(keyword);
	}
	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Category searchCategoryById(Integer id) {
		return (categoryRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category updateCategory(Category category){return categoryRepository.save(category);}

	@Override
	public Page<Category> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.categoryRepository.findAll(pageable);
	}
}
