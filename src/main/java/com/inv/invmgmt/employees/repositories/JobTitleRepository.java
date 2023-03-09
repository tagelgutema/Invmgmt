package com.inv.invmgmt.employees.repositories;

import com.inv.invmgmt.employees.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JobTitleRepository extends JpaRepository<JobTitle,Integer> {

	@Query(value= "select j from  JobTitle j where " +
			"j.jobTitleName LIKE %?1% or j.jobTitleDescription LIKE %?1%")
	List<JobTitle> searchByJobTitle(String keyword);

}