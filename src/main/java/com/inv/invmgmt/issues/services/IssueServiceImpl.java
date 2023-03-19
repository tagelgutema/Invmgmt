package com.inv.invmgmt.issues.services;

import com.inv.invmgmt.issues.model.Issue;
import com.inv.invmgmt.issues.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

	@Service
	public class IssueServiceImpl implements IssueService {


		@Autowired
		private IssueRepository issueRepository;

		//the following getAll method returns all data from Region table from the Database
		@Override
		public List<Issue> getAll() {
			return issueRepository.findAll();
		}

		@Override
		public void addNewIssue(Issue issue) {
			issueRepository.save(issue);

		}

	    @Override
		public List<Issue> searchByIssue(String keyword) {
			return issueRepository.searchByIssue(keyword);
		}

		@Override
		public void saveIssue(Issue issue) {
			issueRepository.save(issue);
		}

		@Override
		public Issue searchIssueById(Long id) {
			return (issueRepository.findById(id).orElse(null));
		}

		@Override
		public void deleteUsingId(Long id) {
			issueRepository.deleteById(id);
		}

		@Override
		public Issue updateIssue(Issue issue) {
			return issueRepository.save(issue);
		}

		@Override
		public Page<Issue> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).descending() :
					Sort.by(sortField).ascending();

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			return this.issueRepository.findAll(pageable);
		}
	}

