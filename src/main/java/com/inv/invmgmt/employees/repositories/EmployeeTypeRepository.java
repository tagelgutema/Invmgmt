package com.inv.invmgmt.employees.repositories;

import com.inv.invmgmt.employees.models.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EmployeeTypeRepository extends JpaRepository<EmployeeType,Integer> {

	@Query(value= "select e from  EmployeeType e where " +
			"e.employeeTypeName LIKE %?1% or e.employeeTypeDescription LIKE %?1%")
	List<EmployeeType> searchByEmployeeType(String keyword);

}