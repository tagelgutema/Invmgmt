package com.inv.invmgmt.settings.services;

import com.inv.invmgmt.settings.models.Department;
import com.inv.invmgmt.settings.models.Region;
import com.inv.invmgmt.settings.repositories.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{


	@Autowired
	private DepartmentRepository departmentRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<Department> getAll() {
		return departmentRepository.findAll();
	}

	@Override
	public void addNewDepartment(Department department) {
		departmentRepository.save(department);

	}
	@Override
	public List<Department> searchByDepartment(String keyword) {
		return departmentRepository.searchByDepartment(keyword);
	}
	@Override
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public Department searchDepartmentById(Integer id) {
		return (departmentRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		departmentRepository.deleteById(id);
	}

	@Override
	public Department updateDepartment(Department department){return departmentRepository.save(department);}

	@Override
	public Page<Department> findPaginated(int pageNo, int pageSize , String sortField, String sortDirection) {
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).descending():
				Sort.by(sortField).ascending();

		Pageable pageable= PageRequest.of(pageNo-1,pageSize,sort);
		return this.departmentRepository.findAll(pageable);
	}
}
