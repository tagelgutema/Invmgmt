package com.inv.invmgmt.settings.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.products.models.Product;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tblSerValue")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "serId")
public class SERValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ser_id")
	private Integer serId;

	@Column(name = "ser_Value")
	private Double serValue;

	@Column(name = "ser_Month")
	private String serMonth;

	public Integer getSerId() {
		return serId;
	}

	public void setSerId(Integer serId) {
		this.serId = serId;
	}

	public Double getSerValue() {
		return serValue;
	}

	public void setSerValue(Double serValue) {
		this.serValue = serValue;
	}

	public String getSerMonth() {
		return serMonth;
	}

	public void setSerMonth(String serMonth) {
		this.serMonth = serMonth;
	}

	@OneToMany(mappedBy = "serid", cascade = CascadeType.ALL)
	private List<Product> product;

}
