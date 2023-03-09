package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.ProductStatus;
import com.inv.invmgmt.products.repositories.ProductStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductStatusServiceImpl implements ProductStatusService {
	@Autowired
	private ProductStatusRepository productStatusRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<ProductStatus> getAll() {
		return productStatusRepository.findAll();
	}

	@Override
	public void addNewProductStatus(ProductStatus productStatus) {
		productStatusRepository.save(productStatus);

	}
	@Override
	public List<ProductStatus> searchByProductStatus(String keyword) {
		return productStatusRepository.searchByProductStatus(keyword);
	}
	@Override
	public void saveProductStatus(ProductStatus productStatus) {
		productStatusRepository.save(productStatus);
	}

	@Override
	public ProductStatus searchProductStatusById(Integer id) {
		return (productStatusRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		productStatusRepository.deleteById(id);
	}

	@Override
	public ProductStatus updateProductStatus(ProductStatus productStatus){return productStatusRepository.save(productStatus);}

	@Override
	public Page<ProductStatus> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.productStatusRepository.findAll(pageable);
	}
}
