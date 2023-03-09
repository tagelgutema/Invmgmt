package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.products.models.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblVendor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vendorId")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private Integer vendorId;

	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "vendor_description")
	private String vendorDescription;

	@Column(name = "vendor_mobile")
	private String vendorMobile;

	@Column(name = "vendor_landline")
	private String vendorLandline;

	@Column(name = "vendor_location")
	private String vendorLocation;

	@OneToMany(mappedBy = "vendorid", cascade = CascadeType.ALL)
	private List<Product> product;

}