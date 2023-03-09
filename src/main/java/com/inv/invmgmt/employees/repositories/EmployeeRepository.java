package com.inv.invmgmt.employees.repositories;

import com.inv.invmgmt.employees.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

	@Query(value= "select e from  Employee e where " +
			"e.firstName LIKE %?1% or e.middleName LIKE %?1% or e.lastName LIKE %?1%")
	List<Employee> searchByEmployee(String keyword);

}