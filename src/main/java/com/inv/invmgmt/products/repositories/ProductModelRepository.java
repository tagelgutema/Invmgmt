package com.inv.invmgmt.products.repositories;

import com.inv.invmgmt.products.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface ProductModelRepository extends JpaRepository<ProductModel,Integer> {

	@Query(value= "select m from  ProductModel m where " +
			"m.modelName LIKE %?1% or m.modelBrand LIKE %?1% or m.modelDescription LIKE %?1%")
	List<ProductModel> searchByProductModel(String keyword);

}
