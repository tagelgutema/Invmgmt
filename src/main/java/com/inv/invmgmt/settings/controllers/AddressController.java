package com.inv.invmgmt.settings.controllers;

import com.inv.invmgmt.settings.models.Address;
import com.inv.invmgmt.settings.models.Region;
import com.inv.invmgmt.settings.services.AddressService;
import com.inv.invmgmt.settings.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private RegionService regionService;

	public Model addModelAttribute(Model model){

		model.addAttribute("regions",regionService.getAll());
		model.addAttribute("address",addressService.getAll());
		return model;
	}

	@GetMapping("/addresses")
	public String getIndexAddress(Model model) {
		addModelAttribute(model);
		List<Address> addresses = addressService.getAll();
		model.addAttribute("addresses", addresses);
		return "settings/addresses/index";
	}

	@GetMapping("/addressAdd")
	public String addressAdd(Model model) {


		addModelAttribute(model);


		return "settings/addresses/addressAdd";
	}

	@PostMapping("/saveAddresses")
	public String saveAddress(@ModelAttribute(value="Address") Address address) {
		addressService.saveAddress(address);
		return "redirect:/addressesList/page";
	}

	@GetMapping("/clearAddressSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"addressId","asc");
	}

	@GetMapping("/searchAddress")
	public String searchByAddress(String keyword,Model model ) {
		List<Address> addresses =null;

		if  (keyword==null){

			return findPaginated(1,model,"addressId","asc");
		} else{
			addresses= addressService.searchByAddress(keyword);
			model.addAttribute("addresses",addresses);
			return "settings/addresses/addresses";}
	}

	@RequestMapping(value ="/addresses/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteAddress(@PathVariable Integer id) {
		this.addressService.deleteUsingId(id);
		return "redirect:/addressesList/page";
	}

	@GetMapping("/addressEdit/{id}")
	public String editAddress(@PathVariable(value="id") Integer id ,Model model){
		//addModelAttribute(model);
		Address address =addressService.searchAddressById(id);
		Integer regId=address.getRegionid();
		List <Region> region= regionService.getAll();
		//Region region=regionService.searchRegionById(regId);
		model.addAttribute("address", address);
		model.addAttribute("regions",region);

		return "settings/addresses/addressEdit";
	}

	@GetMapping("/addressDetail/{id}")
	public String detailAddress(@PathVariable(value="id") Integer id ,Model model){

		//addModelAttribute(model);

		Address address =addressService.searchAddressById(id);
		model.addAttribute("address", address);
		List <Region> regions= regionService.getAll();
		model.addAttribute("regions",regions);
		return "settings/addresses/addressDetail";

	}
	@PostMapping("/updateAddress/{id}")
	public String updateAddress(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("Address") Address address,Model model){

		//get region from database by id
		Address existingAddress= addressService.searchAddressById(id);
		existingAddress.setAddressId(address.getAddressId());
		existingAddress.setDistrictName(address.getDistrictName());
		existingAddress.setWoreda(address.getWoreda());
		existingAddress.setKebele(address.getKebele());
		existingAddress.setHouseNo(address.getHouseNo());
		existingAddress.setAddressDescription(address.getAddressDescription());
		existingAddress.setRegionid(address.getRegionid());

		//save record
		addressService.updateAddress(existingAddress);
		return "redirect:/addressesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/addressesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Address> page=addressService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Address> addresses=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("addresses", addresses);
		return  "settings/addresses/addresses";
	}

	@GetMapping("/addressesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"addressId","asc");
	}
}
