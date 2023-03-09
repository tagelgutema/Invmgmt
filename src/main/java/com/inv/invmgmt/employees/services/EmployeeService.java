package com.inv.invmgmt.employees.services;

import com.inv.invmgmt.employees.models.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

	List<Employee> getAll();
	List<Employee> searchByEmployee(String keyword);
	void saveEmployee(Employee employee);
	void addNewEmployee(Employee employee);
	Employee searchEmployeeById(Integer id);
	void deleteUsingId(Integer id);
	Employee updateEmployee(Employee employee);
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
