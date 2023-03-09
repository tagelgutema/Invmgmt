package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.ProductModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductModelService {

	List<ProductModel> getAll();
	List<ProductModel> searchByProductModel(String keyword);
	void saveProductModel(ProductModel productModel);
	void addNewProductModel(ProductModel productModel);
	ProductModel searchProductModelById(Integer id);
	void deleteUsingId(Integer id);
	ProductModel updateProductModel(ProductModel productModel);
	Page<ProductModel> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}