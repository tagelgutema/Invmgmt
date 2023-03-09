package com.inv.invmgmt.products.models;

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
@Table(name = "tblProductModel")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "modelId")
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Model_id")
	private Integer modelId;

	@Column(name = "model_name")
	private String modelName;

	@Column(name = "model_brand")
	private String modelBrand;

	@Column(name = "model_description")
	private String modelDescription;

	@OneToMany(mappedBy = "modelid", cascade = CascadeType.ALL)
	private List<Product> product;

	@Override
	public String toString() {
		return "ProductModel{" +
				"modelId=" + modelId +
				", modelName='" + modelName + '\'' +
				", modelBrand='" + modelBrand + '\'' +
				", modelDescription='" + modelDescription + '\'' +
				", product=" + product +
				'}';
	}
}