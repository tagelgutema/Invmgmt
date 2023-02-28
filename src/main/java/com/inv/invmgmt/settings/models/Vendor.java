package com.inv.invmgmt.settings.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name = "tblVendor")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vendorId")
public class Vendor  {

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

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}

	public String getVendorMobile() {
		return vendorMobile;
	}

	public void setVendorMobile(String vendorMobile) {
		this.vendorMobile = vendorMobile;
	}

	public String getVendorLandline() {
		return vendorLandline;
	}

	public void setVendorLandline(String vendorLandline) {
		this.vendorLandline = vendorLandline;
	}

	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}

	@Override
	public String toString() {
		return "Vendor{" +
				"vendorId=" + vendorId +
				", vendorName='" + vendorName + '\'' +
				", vendorDescription='" + vendorDescription + '\'' +
				", vendorMobile='" + vendorMobile + '\'' +
				", vendorLandline='" + vendorLandline + '\'' +
				", vendorLocation='" + vendorLocation + '\'' +
				'}';
	}
}
