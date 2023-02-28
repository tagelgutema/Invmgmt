package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Vendor;
import com.inv.invmgmt.settings.repositories.VendorRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendorService {

	List<Vendor> getAll();
    List<Vendor> searchByVendor(String keyword);
	void saveVendor(Vendor vendor);
	void addNewVendor(Vendor vendor);
	Vendor searchVendorById(Integer id);
	void deleteUsingId(Integer id);
	Vendor updateVendor(Vendor vendor);
	Page<Vendor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}


