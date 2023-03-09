package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.ProductModel;
import com.inv.invmgmt.products.repositories.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductModelServiceImpl implements ProductModelService {


	@Autowired
	private ProductModelRepository productModelRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<ProductModel> getAll() {
		return productModelRepository.findAll();
	}

	@Override
	public void addNewProductModel(ProductModel productModel) {
		productModelRepository.save(productModel);

	}
	@Override
	public List<ProductModel> searchByProductModel(String keyword) {
		return productModelRepository.searchByProductModel(keyword);
	}
	@Override
	public void saveProductModel(ProductModel productModel) {
		productModelRepository.save(productModel);
	}

	@Override
	public ProductModel searchProductModelById(Integer id) {
		return (productModelRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		productModelRepository.deleteById(id);
	}

	@Override
	public ProductModel updateProductModel(ProductModel productModel){return productModelRepository.save(productModel);}

	@Override
	public Page<ProductModel> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.productModelRepository.findAll(pageable);
	}
}
