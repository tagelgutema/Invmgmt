package com.inv.invmgmt.settings.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.products.models.Product;
import jakarta.persistence.*;

import java.util.List;

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


	@OneToMany(mappedBy = "currencytypeid", cascade = CascadeType.ALL)
	private List<Product> product;

}
