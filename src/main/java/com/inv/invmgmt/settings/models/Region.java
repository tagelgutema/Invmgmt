package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblRegion")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "regionId")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Integer regionId;

	@Column(name = "region_name")
	private String regionName;

	@Column(name = "region_Description")
	private String regionDescription;

	@OneToMany(mappedBy = "regionid", cascade = CascadeType.ALL)
	private List<Address> address;


}
