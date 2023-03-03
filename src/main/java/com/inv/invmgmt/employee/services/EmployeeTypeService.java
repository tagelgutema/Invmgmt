package com.inv.invmgmt.employee.services;

import com.inv.invmgmt.employee.model.EmployeeType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeTypeService {


	List<EmployeeType> getAll();
	List<EmployeeType> searchByEmployeeType(String keyword);
	void saveEmployeeType(EmployeeType employeeType);
	void addNewEmployeeType(EmployeeType employeeType);
	EmployeeType searchEmployeeTypeById(Integer id);
	void deleteUsingId(Integer id);
	EmployeeType updateEmployeeType(EmployeeType employeeType);
	Page<EmployeeType> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}