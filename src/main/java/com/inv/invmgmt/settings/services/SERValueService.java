package com.inv.invmgmt.settings.services;


import com.inv.invmgmt.settings.models.SERValue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SERValueService {

		List<SERValue> getAll();

		 List<SERValue> searchBySerValue(String keyword);
	 	 void saveSerValue(SERValue serValue);
		 void addNewSerValue(SERValue serValue);
		 SERValue searchSerValueById(Integer id);
		 void deleteUsingId(Integer id);
		 SERValue updateSerValue(SERValue serValue);
		 Page<SERValue> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


	}







