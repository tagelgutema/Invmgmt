package com.inv.invmgmt.products.services;

import com.inv.invmgmt.products.models.Product;
import com.inv.invmgmt.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductRepository productRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public void addNewProduct(Product product) {
		productRepository.save(product);

	}
	@Override
	public List<Product> searchByProduct(String keyword) {
		return productRepository.searchByProduct(keyword);
	}
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product searchProductById(Integer id) {
		return (productRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product){return productRepository.save(product);}

	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.productRepository.findAll(pageable);
	}
}
