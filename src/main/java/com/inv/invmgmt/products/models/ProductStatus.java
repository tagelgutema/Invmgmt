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
@Table(name = "tblProductStatus")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productStatusId")
public class ProductStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productStatus_id")
	private Integer productStatusId;

	@Column(name = "productStatus_name")
	private String productStatusName;

	@Column(name = "productStatus_description")
	private String productStatusDescription;

	@OneToMany(mappedBy = "productstatusid", cascade = CascadeType.ALL)
	private List<Product> product;

}