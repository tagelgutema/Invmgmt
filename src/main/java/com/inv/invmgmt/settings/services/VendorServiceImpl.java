package com.inv.invmgmt.settings.services;


import com.inv.invmgmt.settings.models.Vendor;

import com.inv.invmgmt.settings.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{

	@Autowired
	private VendorRepository vendorRepository;


	@Override
	public List<Vendor> getAll() {
		return vendorRepository.findAll();
	}

	@Override
	public void addNewVendor(Vendor vendor) {
		vendorRepository.save(vendor);

	}
	@Override

	public List<Vendor> searchByVendor(String keyword) {
		return vendorRepository.searchByVendor(keyword);
	}
	@Override
	public void saveVendor(Vendor vendor) {
		vendorRepository.save(vendor);
	}

	@Override
	public Vendor searchVendorById(Integer id) {
		return (Vendor) vendorRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteUsingId(Integer id) {
		vendorRepository.deleteById(id);
	}

	@Override
	public Vendor updateVendor(Vendor vendor){return vendorRepository.save(vendor);}

	@Override
	public Page<Vendor> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.vendorRepository.findAll(pageable);
	}


}

