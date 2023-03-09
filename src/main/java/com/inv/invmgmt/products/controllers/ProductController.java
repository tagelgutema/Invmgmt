package com.inv.invmgmt.products.controllers;

import com.inv.invmgmt.products.models.Category;
import com.inv.invmgmt.products.models.Product;
import com.inv.invmgmt.products.services.CategoryService;
import com.inv.invmgmt.products.services.ProductModelService;
import com.inv.invmgmt.products.services.ProductService;
import com.inv.invmgmt.products.services.ProductStatusService;
import com.inv.invmgmt.settings.services.CurrencyTypeService;
import com.inv.invmgmt.settings.services.SERValueService;
import com.inv.invmgmt.settings.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductModelService productModelService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private VendorService vendorService;

	@Autowired
	private ProductStatusService productStatusService;

	@Autowired
	private SERValueService serValueService;

	@Autowired
	private CurrencyTypeService currencyTypeService;


	public Model addModelAttribute(Model model){

		model.addAttribute("productModels", productModelService.getAll());
		model.addAttribute("categories",categoryService.getAll());
		model.addAttribute("vendors",vendorService.getAll());
		model.addAttribute("productStatuses",productStatusService.getAll());
		model.addAttribute("serValues",serValueService.getAll());
		model.addAttribute("currencyTypes",currencyTypeService.getAll());
		model.addAttribute("products",productService.getAll());
		return model;
	}

	@GetMapping("/products")
	public String getIndexProduct(Model model) {
		addModelAttribute(model);
		List<Product> products = productService.getAll();
		model.addAttribute("products", products);
		return "products/index";
	}

	@GetMapping("/productAdd")
	public String productAdd(Model model) {
		addModelAttribute(model);
		return "products/product/productAdd";
	}

	@PostMapping("/saveProducts")
	public String saveProduct(@ModelAttribute(value="Product") Product product) {
		productService.saveProduct(product);
		return "redirect:/productsList/page";
	}

	@GetMapping("/clearProductSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"productId","asc");
	}

	@GetMapping("/searchProduct")
	public String searchByProduct(String keyword,Model model ) {
		List<Product> products =null;

		if  (keyword==null){

			return findPaginated(1,model,"productId","asc");
		} else{
			products= productService.searchByProduct(keyword);
			model.addAttribute("products",products);
			return "products/product/products";}
	}

	@RequestMapping(value ="/products/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteProduct(@PathVariable Integer id) {
		this.productService.deleteUsingId(id);
		return "redirect:/productsList/page";
	}

	@GetMapping("/productEdit/{id}")
	public String editProduct(@PathVariable(value="id") Integer id ,Model model){
		addModelAttribute(model);
		Product product =productService.searchProductById(id);

		//List <Region> region= regionService.getAll();
		//List<Category> categories=categoryService.getAll();
		model.addAttribute("product", product);

		//model.addAttribute("regions",region);

		return "products/product/productEdit";
	}

	@GetMapping("/productDetail/{id}")
	public String detailProduct(@PathVariable(value="id") Integer id ,Model model){

		//addModelAttribute(model);

		Product product =productService.searchProductById(id);
		model.addAttribute("product", product);
	//	List <Region> regions= regionService.getAll();
		//model.addAttribute("regions",regions);
		return "products/product/productDetail";

	}
	@PostMapping("/updateProduct/{id}")
	public String updateProduct(@PathVariable(value="id") Integer id ,
								@ModelAttribute("Product") Product product,Model model){

		//get region from database by id
		Product existingProduct= productService.searchProductById(id);
		existingProduct.setProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductDescription(product.getProductDescription());

		existingProduct.setProductionDate(product.getProductionDate());
		existingProduct.setPurchaseDate(product.getPurchaseDate());
		existingProduct.setProductPrice(product.getProductPrice());
		existingProduct.setAbacusNumber(product.getAbacusNumber());
		existingProduct.setSerialNumber(product.getSerialNumber());
		existingProduct.setPsmTag(product.getPsmTag());


		existingProduct.setProductCompliance(product.getProductCompliance());
		
		existingProduct.setCategoryid(product.getCategoryid());
		existingProduct.setVendorid(product.getVendorid());
		existingProduct.setProductstatusid(product.getProductstatusid());
		existingProduct.setSerid(product.getSerid());
		existingProduct.setCurrencytypeid(product.getCurrencytypeid());
		existingProduct.setProductModel(product.getProductModel());
		existingProduct.setProductRemark(product.getProductRemark());


		//save record
		productService.updateProduct(existingProduct);
		return "redirect:/productsList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/productsList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Product> page=productService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Product> products=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("products", products);
		return  "products/product/products";
	}

	@GetMapping("/productsList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"productId","asc");
	}
}
