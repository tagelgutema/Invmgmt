package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {

	List<Product> getAll();
	List<Product> searchByProduct(String keyword);
	void saveProduct(Product product);
	void addNewProduct(Product product);
	Product searchProductById(Integer id);
	void deleteUsingId(Integer id);
	Product updateProduct(Product product);
	Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}