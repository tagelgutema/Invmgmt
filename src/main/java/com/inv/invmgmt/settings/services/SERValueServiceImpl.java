package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.SERValue;
import com.inv.invmgmt.settings.repositories.SERValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SERValueServiceImpl implements SERValueService {

	@Autowired
	private SERValueRepository serValueRepository;
	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<SERValue> getAll() {
		return serValueRepository.findAll();
	}

	@Override
	public void addNewSerValue(SERValue serValue) {
		serValueRepository.save(serValue);

	}
	@Override
	public List<SERValue> searchBySerValue(String keyword) {
		return serValueRepository.searchBySERValue(keyword);
	}
	@Override
	public void saveSerValue(SERValue serValue) {
		serValueRepository.save(serValue);
	}

	@Override
	public SERValue searchSerValueById(Integer id) {
		return (serValueRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		serValueRepository.deleteById(id);
	}

	@Override
	public SERValue updateSerValue(SERValue serValue){return serValueRepository.save(serValue);}

	@Override
	public Page<SERValue> findPaginated(int pageNo, int pageSize ,String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.serValueRepository.findAll(pageable);
	}

}
