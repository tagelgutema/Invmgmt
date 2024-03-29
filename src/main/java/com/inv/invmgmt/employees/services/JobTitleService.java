package com.inv.invmgmt.employees.services;

import com.inv.invmgmt.employees.models.JobTitle;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobTitleService {

	List<JobTitle> getAll();
	List<JobTitle> searchByJobTitle(String keyword);
	void saveJobTitle(JobTitle jobTitle);
	void addNewJobTitle(JobTitle jobTitle);
	JobTitle searchJobTitleById(Integer id);
	void deleteUsingId(Integer id);
	JobTitle updateJobTitle(JobTitle jobTitle);
	Page<JobTitle> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}