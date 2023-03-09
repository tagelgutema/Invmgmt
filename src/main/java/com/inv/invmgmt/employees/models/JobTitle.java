package com.inv.invmgmt.employees.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblJobTitle")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "jobTitleId")
public class JobTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobTitleId;

	@Column(name = "jobTitle_name")
	private String jobTitleName;

	@Column(name = "jobTitle_description")
	private String jobTitleDescription;

	@OneToMany(mappedBy = "jobtitleid", cascade = CascadeType.ALL)
	private List<Employee> employee;

}
