package com.inv.invmgmt.products.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.inv.invmgmt.settings.models.CurrencyType;
import com.inv.invmgmt.settings.models.SERValue;
import com.inv.invmgmt.settings.models.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.h2.util.Bits;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblProduct")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String productDescription;

	@Column(name = "production_Date")
	@DateTimeFormat(pattern =  "yyyy-MM-dd")
	private Date productionDate;

	@Column(name = "purchase_date" )
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date purchaseDate;

	@Column(name = "product_price" )
	private Double productPrice;

	@Column(name = "abacus_Number")
	private String abacusNumber;

	@Column(name = "product_serialnumber")
	private String serialNumber;

	@Column(name = "psm_tag")
	private String psmTag;

	@Column(name = "product_compliance")
	private String productCompliance;

	@ManyToOne
	@JoinColumn(name="categoryid", insertable = false,updatable = false)
	private Category category;
	private Integer categoryid;


	@ManyToOne
	@JoinColumn(name="vendorid", insertable = false,updatable = false)
	private Vendor vendor;
	private Integer vendorid;

	@ManyToOne
	@JoinColumn(name="productstatusid", insertable = false,updatable = false)
	private ProductStatus productStatus;
	private Integer productstatusid;

	@ManyToOne
	@JoinColumn(name="serid", insertable = false,updatable = false)
	private SERValue serValue;
	private Integer serid;

	@ManyToOne
	@JoinColumn(name="currencytypeid", insertable = false,updatable = false)
	private CurrencyType currencyType;
	private Integer  currencytypeid;

	@ManyToOne
	@JoinColumn(name="modelid", insertable = false,updatable = false)
	private ProductModel productModel;
	private Integer  modelid;

	@Column(name = "product_remark")
	private String productRemark;

	@Column(name = "product_Current" )
	private String productCurrent;


}