package com.inv.invmgmt.products.repositories;


import com.inv.invmgmt.products.models.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductStatusRepository extends JpaRepository<ProductStatus,Integer> {

	@Query(value= "select p  from  ProductStatus p where " +
			"p.productStatusName LIKE %?1% or p.productStatusDescription LIKE %?1%")
	List<ProductStatus> searchByProductStatus(String keyword);

}