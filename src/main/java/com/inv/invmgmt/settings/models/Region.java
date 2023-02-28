package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

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

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

	public int getRegionId() {
		return regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public String getRegionDescription() {
		return regionDescription;
	}

	@Override
	public String toString() {
		return "Region{" +
				"regionId=" + regionId +
				", regionName='" + regionName + '\'' +
				", regionDescription='" + regionDescription + '\'' +
				'}';
	}
}
