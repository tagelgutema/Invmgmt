package com.inv.invmgmt.issues.repositories;

import com.inv.invmgmt.issues.model.Issue;
import com.inv.invmgmt.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue,Long> {

	@Query(value = "SELECT * FROM tbl_issues i WHERE " +
			"EXISTS (SELECT * FROM tbl_product p WHERE i.productid = p.product_id AND (p.product_name = :keyword " +
			"OR  p.product_serialnumber = :keyword  OR  p.psm_tag = :keyword  OR p.product_description = :keyword ))",
	nativeQuery=true)
	List<Issue> searchByIssue(String keyword);

}

