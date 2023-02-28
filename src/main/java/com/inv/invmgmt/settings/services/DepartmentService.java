package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Department;

import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {


       List <Department> getAll();

	List<Department> searchByDepartment(String keyword);
	void saveDepartment(Department region);
	void addNewDepartment(Department department);
	Department searchDepartmentById(Integer id);
	void deleteUsingId(Integer id);
	Department updateDepartment(Department department);
	Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}


