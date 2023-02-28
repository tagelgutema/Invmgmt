package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "tblNxpValue")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nxpValueId")
public class NXPValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nxpValue_id")
	private Integer nxpValueId;

	@Column(name = "nxpValue")
	private Double nxpValue;

	@Column(name = "nxpValue_Year")
	private String nxpValueYear;

	@Column(name = "nxpValue_Description")
	private String nxpValueDescription;

	public NXPValue() {
	}

	public NXPValue(Integer nxpValueId, Double nxpValue, String nxpValueYear, String nxpValueDescription) {
		this.nxpValueId = nxpValueId;
		this.nxpValue = nxpValue;
		this.nxpValueYear = nxpValueYear;
		this.nxpValueDescription = nxpValueDescription;
	}

	public Integer getNxpValueId() {
		return nxpValueId;
	}

	public void setNxpValueId(Integer nxpValueId) {
		this.nxpValueId = nxpValueId;
	}

	public Double getNxpValue() {
		return nxpValue;
	}

	public void setNxpValue(Double nxpValue) {
		this.nxpValue = nxpValue;
	}

	public String getNxpValueYear() {
		return nxpValueYear;
	}

	public void setNxpValueYear(String nxpValueYear) {
		this.nxpValueYear = nxpValueYear;
	}

	public String getNxpValueDescription() {
		return nxpValueDescription;
	}

	public void setNxpValueDescription(String nxpValueDescription) {
		this.nxpValueDescription = nxpValueDescription;
	}

	@Override
	public String toString() {
		return "NXPValue{" +
				"nxpValueId=" + nxpValueId +
				", nxpValue='" + nxpValue + '\'' +
				", nxpValueYear='" + nxpValueYear + '\'' +
				", nxpValueDescription='" + nxpValueDescription + '\'' +
				'}';
	}
}
