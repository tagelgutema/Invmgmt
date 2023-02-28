package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Region;
import com.inv.invmgmt.settings.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Region> getAll() {
		return regionRepository.findAll();
	}

	@Override
	public void addNewRegion(Region region) {
		regionRepository.save(region);

	}
	@Override
	public List<Region> searchByRegion(String keyword) {
		return regionRepository.searchByRegion(keyword);
	}
	@Override
	public void saveRegion(Region region) {
		regionRepository.save(region);
	}

	@Override
	public Region searchRegionById(Integer id) {
		return (regionRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		regionRepository.deleteById(id);
	}

	@Override
	public Region updateRegion(Region region){return regionRepository.save(region);}

	@Override
	public Page<Region> findPaginated(int pageNo, int pageSize ,String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.regionRepository.findAll(pageable);
	}


}
