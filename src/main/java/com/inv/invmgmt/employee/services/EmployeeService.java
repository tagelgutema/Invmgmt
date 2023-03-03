package com.inv.invmgmt.employee.services;

import com.inv.invmgmt.employee.model.Employee;
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
