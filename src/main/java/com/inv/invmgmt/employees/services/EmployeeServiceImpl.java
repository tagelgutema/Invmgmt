package com.inv.invmgmt.employees.services;

import com.inv.invmgmt.employees.models.Employee;
import com.inv.invmgmt.employees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void addNewEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public List<Employee> searchByEmployee(String keyword) {
		return employeeRepository.searchByEmployee(keyword);
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Employee searchEmployeeById(Integer id) {
		return (employeeRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).descending() :
				Sort.by(sortField).ascending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}
}