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
@Table(name = "tblEmpoyeeType")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeTypeId")
public class EmployeeType {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer employeeTypeId;

		@Column(name = "employeeType_name")
		private String employeeTypeName;

		@Column(name = "employeeType_description")
		private String employeeTypeDescription;

		@OneToMany(mappedBy = "employeetypeid", cascade = CascadeType.ALL)
		private List<Employee> employee;


}
