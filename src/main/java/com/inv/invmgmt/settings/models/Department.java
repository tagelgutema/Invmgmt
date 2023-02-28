package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;


@Entity
@Table(name = "tblDepartment")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "deptId")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private Integer deptId;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "dept_Description")
	private String deptDescription;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDescription() {
		return deptDescription;
	}

	public void setDeptDescription(String deptDescription) {
		this.deptDescription = deptDescription;
	}

	@Override
	public String toString() {
		return "Department{" +
				"deptId=" + deptId +
				", deptName='" + deptName + '\'' +
				", deptDescription='" + deptDescription + '\'' +
				'}';
	}
}
