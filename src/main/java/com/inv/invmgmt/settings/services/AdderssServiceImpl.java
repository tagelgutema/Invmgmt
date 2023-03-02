package com.inv.invmgmt.settings.services;


import com.inv.invmgmt.settings.models.Address;
import com.inv.invmgmt.settings.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdderssServiceImpl implements  AddressService {


	@Autowired
	private AddressRepository addressRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Address> getAll() {
		return addressRepository.findAll();
	}

	@Override
	public void addNewAddress(Address address) {
		addressRepository.save(address);

	}
	@Override
	public List<Address> searchByAddress(String keyword) {
		return addressRepository.searchByAddress(keyword);
	}
	@Override
	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public Address searchAddressById(Integer id) {
		return (addressRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Address updateAddress(Address address){return addressRepository.save(address);}

	@Override
	public Page<Address> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.addressRepository.findAll(pageable);
	}

}
