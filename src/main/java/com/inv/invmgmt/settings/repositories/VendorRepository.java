package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

		@Query(value= "select v  from  Vendor v where " +
		"v.vendorName LIKE %?1% or v.vendorDescription LIKE %?1%")
		List<Vendor> searchByVendor(String keyword);


}
