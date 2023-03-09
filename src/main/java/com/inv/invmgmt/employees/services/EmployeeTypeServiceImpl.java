package com.inv.invmgmt.employees.services;

import com.inv.invmgmt.employees.models.EmployeeType;
import com.inv.invmgmt.employees.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {


	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<EmployeeType> getAll() {
		return employeeTypeRepository.findAll();
	}

	@Override
	public void addNewEmployeeType(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);

	}

	@Override
	public List<EmployeeType> searchByEmployeeType(String keyword) {
		return employeeTypeRepository.searchByEmployeeType(keyword);
	}

	@Override
	public void saveEmployeeType(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
	}

	@Override
	public EmployeeType searchEmployeeTypeById(Integer id) {
		return (employeeTypeRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		employeeTypeRepository.deleteById(id);
	}

	@Override
	public EmployeeType updateEmployeeType(EmployeeType employeeType) {
		return employeeTypeRepository.save(employeeType);
	}

	@Override
	public Page<EmployeeType> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).descending() :
				Sort.by(sortField).ascending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeTypeRepository.findAll(pageable);
	}
}