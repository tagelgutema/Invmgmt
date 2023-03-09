package com.inv.invmgmt.employees.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.settings.models.Address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblEmployee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "pin_no")
	private String pinNo;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "middlename")
	private String middleName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phoneno")
	private String phoneNo;

	@Column(name = "address_description")
	private String addressDescription;

	@ManyToOne
	@JoinColumn(name = "jobtitleid", insertable = false, updatable = false)
	private JobTitle jobTitle;
	private Integer jobtitleid;

	@ManyToOne
	@JoinColumn(name = "employeetypeid", insertable = false, updatable = false)
	private EmployeeType employeeType;
	private Integer employeetypeid;

	@ManyToOne
	@JoinColumn(name = "addressid", insertable = false, updatable = false)
	private Address address;
	private Integer addressid;


}