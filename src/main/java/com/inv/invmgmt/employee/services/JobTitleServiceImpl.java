package com.inv.invmgmt.employee.services;

import com.inv.invmgmt.employee.model.JobTitle;
import com.inv.invmgmt.employee.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobTitleServiceImpl implements JobTitleService {


	@Autowired
	private JobTitleRepository jobTitleRepository;

	//the following getAll method returns all data from Region table from the Database
	@Override
	public List<JobTitle> getAll() {
		return jobTitleRepository.findAll();
	}

	@Override
	public void addNewJobTitle(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);

	}

	@Override
	public List<JobTitle> searchByJobTitle(String keyword) {
		return jobTitleRepository.searchByJobTitle(keyword);
	}

	@Override
	public void saveJobTitle(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
	}

	@Override
	public JobTitle searchJobTitleById(Integer id) {
		return (jobTitleRepository.findById(id).orElse(null));
	}

	@Override
	public void deleteUsingId(Integer id) {
		jobTitleRepository.deleteById(id);
	}

	@Override
	public JobTitle updateJobTitle(JobTitle jobTitle) {
		return jobTitleRepository.save(jobTitle);
	}

	@Override
	public Page<JobTitle> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).descending() :
				Sort.by(sortField).ascending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.jobTitleRepository.findAll(pageable);
	}
}