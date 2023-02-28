package com.inv.invmgmt.settings.services;


import com.inv.invmgmt.settings.models.NXPValue;
import com.inv.invmgmt.settings.repositories.NXPValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NXPValueServiceImpl implements  NXPValueService{


	@Autowired
	private NXPValueRepository nxpValueRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<NXPValue> getAll() {
		return nxpValueRepository.findAll();
	}

	@Override
	public void addNewNXPValue(NXPValue nxpValue) {
		nxpValueRepository.save(nxpValue);

	}
	@Override
	public List<NXPValue> searchByNXPValue(String keyword) {
		return nxpValueRepository.searchByNXPValue(keyword);
	}
	@Override
	public void saveNXPValue(NXPValue nxpValue) {
		nxpValueRepository.save(nxpValue);
	}

	@Override
	public NXPValue searchNXPValueById(Integer id) {
		return (nxpValueRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		nxpValueRepository.deleteById(id);
	}

	@Override
	public NXPValue updateNXPValue(NXPValue nxpValue){return nxpValueRepository.save(nxpValue);}

	@Override
	public Page<NXPValue> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.nxpValueRepository.findAll(pageable);
	}
}
