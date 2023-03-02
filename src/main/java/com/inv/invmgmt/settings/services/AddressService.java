package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Address;
import com.inv.invmgmt.settings.models.Address;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {

	List<Address> getAll();
	List<Address> searchByAddress (String keyword);
	void saveAddress(Address address);
	void addNewAddress(Address address);
	Address searchAddressById(Integer id);
	void deleteUsingId(Integer id);
	Address updateAddress(Address address);
	Page<Address> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);



}
