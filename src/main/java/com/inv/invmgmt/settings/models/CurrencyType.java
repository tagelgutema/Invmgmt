package com.inv.invmgmt.settings.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "tblCurrencyType")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "currencyTypeId")
public class CurrencyType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer currencyTypeId;

	@Column(name = "currencyType_name")
	private String currencyTypeName;

	@Column(name = "currencyType_Description")
	private String currencyTypeDescription;

	public Integer getCurrencyTypeId() {
		return currencyTypeId;
	}

	public void setCurrencyTypeId(Integer currencyTypeId) {
		this.currencyTypeId = currencyTypeId;
	}

	public String getCurrencyTypeName() {
		return currencyTypeName;
	}

	public void setCurrencyTypeName(String currencyTypeName) {
		this.currencyTypeName = currencyTypeName;
	}

	public String getCurrencyTypeDescription() {
		return currencyTypeDescription;
	}

	public void setCurrencyTypeDescription(String currencyTypeDescription) {
		this.currencyTypeDescription = currencyTypeDescription;
	}

	@Override
	public String toString() {
		return "CurrencyType{" +
				"currencyTypeId=" + currencyTypeId +
				", currencyTypeName='" + currencyTypeName + '\'' +
				", currencyTypeDescription='" + currencyTypeDescription + '\'' +
				'}';
	}
}
