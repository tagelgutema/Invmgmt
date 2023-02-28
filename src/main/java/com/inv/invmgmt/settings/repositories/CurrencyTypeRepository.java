package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyTypeRepository extends JpaRepository <CurrencyType,Integer> {

	@Query(value= "select c  from  CurrencyType c where " +
			"c.currencyTypeName LIKE %?1% or c.currencyTypeDescription LIKE %?1%")
	List<CurrencyType> searchByCurrencyType(String keyword);
}
