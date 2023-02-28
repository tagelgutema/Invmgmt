package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Region;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegionService {

		List<Region> getAll();

		List<Region> searchByRegion(String keyword);
	 	void saveRegion(Region region);
		 void addNewRegion(Region region);
		 Region searchRegionById(Integer id);
		 void deleteUsingId(Integer id);
		Region updateRegion(Region region);
		Page<Region> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


	}







