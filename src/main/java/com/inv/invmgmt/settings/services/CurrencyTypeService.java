package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.CurrencyType;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CurrencyTypeService {

	List<CurrencyType> getAll();

	List<CurrencyType> searchByCurrencyType(String keyword);
	void saveCurrencyType(CurrencyType currencyType);
	void addNewCurrencyType(CurrencyType currencyType);
	CurrencyType searchCurrencyTypeById(Integer id);
	void deleteUsingId(Integer id);
	CurrencyType updateCurrencyType(CurrencyType currencyType);
	Page<CurrencyType> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
