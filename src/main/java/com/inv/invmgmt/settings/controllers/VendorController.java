package com.inv.invmgmt.settings.controllers;


import com.inv.invmgmt.settings.models.Vendor;
import com.inv.invmgmt.settings.services.VendorServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VendorController {

	@Autowired
	private VendorServiceImpl vendorService;


	@GetMapping("/vendorsAdd")
	public String vendorsAdd() {return "settings/vendors/vendorAdd";}


	@GetMapping("/vendors")
	public String getIndexDepartment(Model model) {
		List<Vendor> vendors = vendorService.getAll();
		model.addAttribute("vendors", vendors);
		return "settings/vendors/index";
	}


	@PostMapping("/saveVendors")
	public String saveVendors(@ModelAttribute(value="Vendor") Vendor vendor) {
		vendorService.saveVendor(vendor);
		return "redirect:/vendorsList/page";
	}

	@GetMapping("/clearVendorSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"vendorId","asc");
	}

	@GetMapping("/searchVendor")
	public String searchByVendors(String keyword,Model model ) {
		List<Vendor> vendors=null;

		if  (keyword==null){

			return findPaginated(1,model,"vendorId","asc");
		} else{
			vendors= vendorService.searchByVendor(keyword);
			model.addAttribute("vendors",vendors);
			return "settings/vendors/vendors";}
	}

	@RequestMapping(value ="/vendors/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteVendor(@PathVariable Integer id) {
		this.vendorService.deleteUsingId(id);
		return "redirect:/vendorsList/page";
	}

	@GetMapping("/vendorEdit/{id}")
	public String editVendor(@PathVariable(value="id") Integer id ,Model model){

		Vendor vendor =vendorService.searchVendorById(id);
		model.addAttribute("vendor", vendor);
		return "settings/vendors/vendorEdit";
	}

	@GetMapping("/vendorDetail/{id}")
	public String detailVendor(@PathVariable(value="id") Integer id ,Model model){

		Vendor vendor =vendorService.searchVendorById(id);
		model.addAttribute("vendor", vendor);
		return "settings/vendors/vendorDetail";
	}


	@PostMapping("/updateVendor/{id}")
	public String updateVendor(@PathVariable(value="id") Integer id ,
							   @ModelAttribute("vendor") Vendor vendor,Model model){

		//get vendor from database by id
		Vendor existingVendor= vendorService.searchVendorById(id);
		existingVendor.setVendorId(vendor.getVendorId());
		existingVendor.setVendorName(vendor.getVendorName());
		existingVendor.setVendorDescription(vendor.getVendorDescription());
		existingVendor.setVendorMobile(vendor.getVendorMobile());
		existingVendor.setVendorLandline(vendor.getVendorLandline());
		existingVendor.setVendorLocation(vendor.getVendorLocation());
		//save record
		vendorService.updateVendor(existingVendor);
		return "redirect:/vendorsList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/vendorsList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Vendor> page=vendorService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Vendor> vendors=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("vendors", vendors);
		return  "settings/vendors/vendors";
	}

	@GetMapping("/vendorsList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"vendorId","asc");
	}




}
