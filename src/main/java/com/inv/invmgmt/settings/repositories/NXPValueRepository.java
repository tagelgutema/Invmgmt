package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.NXPValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NXPValueRepository extends JpaRepository<NXPValue,Integer> {

	@Query(value= "select n from  NXPValue n where " +
			"n.nxpValueYear LIKE %?1% or n.nxpValueDescription LIKE %?1%")
	List<NXPValue> searchByNXPValue(String keyword);
}
