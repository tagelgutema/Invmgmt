package com.inv.invmgmt.issues.services;

import com.inv.invmgmt.issues.model.Issue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IssueService {

	List<Issue> getAll();
	List<Issue> searchByIssue(String keyword);
	void saveIssue(Issue issue);
	void addNewIssue(Issue issue);
	Issue searchIssueById(Long id);
	void deleteUsingId(Long id);
	Issue updateIssue(Issue issue);
	Page<Issue> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}

