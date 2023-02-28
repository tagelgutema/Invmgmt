package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.NXPValue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NXPValueService {


	List<NXPValue> getAll();

	List<NXPValue> searchByNXPValue(String keyword);
	void saveNXPValue(NXPValue nxpValue);
	void addNewNXPValue(NXPValue nxpValue);
	NXPValue searchNXPValueById(Integer id);
	void deleteUsingId(Integer id);
	NXPValue updateNXPValue(NXPValue nxpValue);
	Page<NXPValue> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
