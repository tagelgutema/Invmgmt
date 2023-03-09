package com.inv.invmgmt.products.controllers;

import com.inv.invmgmt.products.models.ProductModel;
import com.inv.invmgmt.products.services.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductModelController {

	@Autowired
	private ProductModelService productModelService;

	@GetMapping("/productModels")
	public String getIndexProductModel(Model model) {
		List<ProductModel> productModels = productModelService.getAll();
		model.addAttribute("productModels", productModels);
		return "products/productModel/index";
	}

	@GetMapping("/productModelAdd")
	public String productModelAdd() {
		return "products/productModel/productModelAdd";
	}

	@PostMapping("/saveProductModels")
	public String saveProductModel(@ModelAttribute(value="ProductModel") ProductModel productModel) {
		productModelService.saveProductModel(productModel);
		return "redirect:/productModelsList/page";
	}

	@GetMapping("/clearProductModelSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"modelId","asc");
	}

	@GetMapping("/searchProductModel")
	public String searchByProductModel(String keyword,Model model ) {
		List<ProductModel> productModels =null;

		if  (keyword==null){

			return findPaginated(1,model,"modelId","asc");
		} else{
			productModels= productModelService.searchByProductModel(keyword);
			model.addAttribute("productModels",productModels);
			return "products/productModel/productModels";}
	}

	@RequestMapping(value ="/productModels/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteProductModel(@PathVariable Integer id) {
		this.productModelService.deleteUsingId(id);
		return "redirect:/productModelsList/page";
	}

	@GetMapping("/productModelEdit/{id}")
	public String editProductModel(@PathVariable(value="id") Integer id ,Model model){

		ProductModel productModel =productModelService.searchProductModelById(id);
		model.addAttribute("productModel", productModel);
		return "products/productModel/productModelEdit";
	}

	@GetMapping("/productModelDetail/{id}")
	public String detailProductModel(@PathVariable(value="id") Integer id ,Model model){

		ProductModel productModel =productModelService.searchProductModelById(id);
		model.addAttribute("productModel", productModel);
		return "products/productModel/productModelDetail";
	}


	@PostMapping("/updateProductModel/{id}")
	public String updateProductModel(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("ProductModel") ProductModel productModel,Model model){

		//get region from database by id
		ProductModel existingProductModel= productModelService.searchProductModelById(id);
		existingProductModel.setModelId(productModel.getModelId());
		existingProductModel.setModelName(productModel.getModelName());
		existingProductModel.setModelBrand(productModel.getModelBrand());
		existingProductModel.setModelDescription(productModel.getModelDescription());
		//save record
		productModelService.updateProductModel(existingProductModel);
		return "redirect:/productModelsList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/productModelsList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<ProductModel> page=productModelService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<ProductModel> productModels=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("productModels", productModels);
		return  "products/productModel/productModels";
	}

	@GetMapping("/productModelsList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"modelId","asc");
	}
}

