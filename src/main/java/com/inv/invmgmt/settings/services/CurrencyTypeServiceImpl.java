package com.inv.invmgmt.settings.services;


import com.inv.invmgmt.settings.models.CurrencyType;
import com.inv.invmgmt.settings.repositories.CurrencyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyTypeServiceImpl implements  CurrencyTypeService{


	@Autowired
	private CurrencyTypeRepository currencyTypeRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<CurrencyType> getAll() {
		return currencyTypeRepository.findAll();
	}

	@Override
	public void addNewCurrencyType(CurrencyType currencyType) {
		currencyTypeRepository.save(currencyType);

	}
	@Override
	public List<CurrencyType> searchByCurrencyType(String keyword) {
		return currencyTypeRepository.searchByCurrencyType(keyword);
	}
	@Override
	public void saveCurrencyType(CurrencyType currencyType) {
		currencyTypeRepository.save(currencyType);
	}

	@Override
	public CurrencyType searchCurrencyTypeById(Integer id) {
		return (currencyTypeRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		currencyTypeRepository.deleteById(id);
	}

	@Override
	public CurrencyType updateCurrencyType(CurrencyType currencyType){return currencyTypeRepository.save(currencyType);}

	@Override
	public Page<CurrencyType> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.currencyTypeRepository.findAll(pageable);
	}
}
