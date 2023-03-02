package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblAddress")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	@Column(name = "district_name")
	private String districtName;

	@Column(name = "woreda")
	private String woreda;

	@Column(name = "kebele")
	private String kebele;

	@Column(name = "hourse_no")
	private String houseNo;

	@Column(name = "address_description")
	private String addressDescription;

	@ManyToOne
	@JoinColumn(name="regionid", insertable = false,updatable = false)
	private Region region;

	private Integer regionid;


}
