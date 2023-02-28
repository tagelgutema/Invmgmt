package com.inv.invmgmt.settings.repositories;

import com.inv.invmgmt.settings.models.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {


	@Query(value= "select d  from  Department d where " +
			"d.deptName LIKE %?1% or d.deptDescription LIKE %?1%")
	List<Department> searchByDepartment(String keyword);


  /*  @Query(value="SELECT r FROM Region r WHERE CONCAT(r.regionId or r.regionName or r.regionDescription) LIKE %?1%")
    List<Region> searchByRegion(String keyword);
*/
}


