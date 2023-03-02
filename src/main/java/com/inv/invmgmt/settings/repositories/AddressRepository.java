package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository <Address,Integer>{

	@Query(value= "select a  from  Address a where " +
			"a.districtName LIKE %?1%  or a.addressDescription LIKE %?1%")
	List<Address> searchByAddress(String keyword);
}
