package com.inv.invmgmt.products.repositories;

import com.inv.invmgmt.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

	@Query(value= "select p  from  Product p where " +
			"p.productName LIKE %?1% or p.serialNumber  LIKE %?1%  " +
			"or p.productDescription LIKE %?1%  or p.abacusNumber LIKE %?1%  or p.psmTag LIKE %?1%")
	List<Product> searchByProduct(String keyword);

}
