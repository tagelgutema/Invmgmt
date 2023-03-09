package com.inv.invmgmt.products.controllers;

import com.inv.invmgmt.products.models. ProductStatus;
import com.inv.invmgmt.products.services. ProductStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProductStatusController {

		@Autowired
		private ProductStatusService productStatusService;

		@GetMapping("/productStatuss")
		public String getIndexProductStatus(Model model) {
			List<ProductStatus> productStatuss = productStatusService.getAll();
			model.addAttribute("productStatuss", productStatuss);
			return "products/productStatuss/index";
		}

		@GetMapping("/productStatusAdd")
		public String productStatusAdd() {
			return "products/productStatus/productStatusAdd";
		}

		@PostMapping("/saveProductStatuss")
		public String saveProductStatus(@ModelAttribute(value="ProductStatus") ProductStatus productStatus) {
			productStatusService.saveProductStatus(productStatus);
			return "redirect:/productStatussList/page";
		}

		@GetMapping("/clearProductStatusSearch")
		public String clearSearch(Model model){

			return findPaginated(1,model,"productStatusId","asc");
		}

		@GetMapping("/searchProductStatus")
		public String searchByProductStatus(String keyword,Model model ) {
			List<ProductStatus> productStatuss =null;

			if  (keyword==null){

				return findPaginated(1,model,"productStatusId","asc");
			} else{
				productStatuss= productStatusService.searchByProductStatus(keyword);
				model.addAttribute("productStatuss",productStatuss);
				return "products/productStatuss/productStatuss";}
		}

		@RequestMapping(value ="/productStatuss/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
		public String deleteProductStatus(@PathVariable Integer id) {
			this.productStatusService.deleteUsingId(id);
			return "redirect:/productStatussList/page";
		}

		@GetMapping("/productStatusEdit/{id}")
		public String editProductStatus(@PathVariable(value="id") Integer id ,Model model){

			ProductStatus productStatus =productStatusService.searchProductStatusById(id);
			model.addAttribute("productStatus", productStatus);
			return "products/productStatuss/productStatusEdit";
		}

		@GetMapping("/productStatusDetail/{id}")
		public String detailProductStatus(@PathVariable(value="id") Integer id ,Model model){

			ProductStatus productStatus =productStatusService.searchProductStatusById(id);
			model.addAttribute("productStatus", productStatus);
			return "products/productStatus/productStatusDetail";
		}


		@PostMapping("/updateProductStatus/{id}")
		public String updateProductStatus(@PathVariable(value="id") Integer id ,
									   @ModelAttribute("ProductStatus") ProductStatus productStatus,Model model){

			//get region from database by id
			ProductStatus existingProductStatus= productStatusService.searchProductStatusById(id);
			existingProductStatus.setProductStatusId(productStatus.getProductStatusId());
			existingProductStatus.setProductStatusName(productStatus.getProductStatusName());
			existingProductStatus.setProductStatusDescription(productStatus.getProductStatusDescription());
			//save record
			productStatusService.updateProductStatus(existingProductStatus);
			return "redirect:/productStatussList/page";
		}
		// /page/1?sortFied=name&sortDir=asc
		@GetMapping("/productStatussList/page/{pageNo}")
		public String findPaginated(@PathVariable (value="pageNo") int pageNo,
									Model model,
									@RequestParam("sortField") String sortField,
									@RequestParam("sortDir") String sortDir){

			int pageSize=5;
			Page<ProductStatus> page=productStatusService.findPaginated(pageNo,pageSize,sortField,sortField);
			List<ProductStatus> productStatuss=page.getContent();
			model.addAttribute("currentPage",pageNo);
			model.addAttribute("totalPages",page.getTotalPages());
			model.addAttribute("totalItems",page.getTotalElements());

			model.addAttribute("sortField",sortField);
			model.addAttribute("sortDir",sortDir);
			model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

			model.addAttribute("productStatuss", productStatuss);
			return  "products/productStatus/productStatuss";
		}

		@GetMapping("/productStatussList/page")
		public String getAllPages(Model model ){
			return findPaginated(1,model,"productStatusId","asc");
		}
	}