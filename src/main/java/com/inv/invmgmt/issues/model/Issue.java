package com.inv.invmgmt.issues.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.employees.models.Employee;
import com.inv.invmgmt.products.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblIssues")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "issueId")
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long issueId;

	@ManyToOne
	@JoinColumn(name = "employeeid", insertable = false, updatable = false)
	private Employee employee;
	private Integer employeeid;

	@ManyToOne
	@JoinColumn(name = "productid", insertable = false, updatable = false)
	private Product product;
	private Integer productid;


	@Column(name = "production_Date")
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date issueDate;

	@JoinColumn(name = "issued_by")
	private String issuedBy;


	@JoinColumn(name = "authorized_by")
	private String authorizedBy;

	@JoinColumn(name = "remark")
	private String remark;

}