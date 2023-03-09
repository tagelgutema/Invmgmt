package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.ProductStatus;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductStatusService {

	List<ProductStatus> getAll();
	List<ProductStatus> searchByProductStatus(String keyword);
	void saveProductStatus(ProductStatus productStatus);
	void addNewProductStatus(ProductStatus productStatus);
	ProductStatus searchProductStatusById(Integer id);
	void deleteUsingId(Integer id);
	ProductStatus updateProductStatus(ProductStatus productStatus);
	Page<ProductStatus> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}