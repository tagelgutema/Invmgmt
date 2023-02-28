package com.inv.invmgmt.settings.controllers;

import com.inv.invmgmt.settings.models.CurrencyType;
import com.inv.invmgmt.settings.services.CurrencyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CurrencyTypeController {


	@Autowired
	private CurrencyTypeService currencyTypeService;

	@GetMapping("/currencyTypes")
	public String getIndexCurrencyType(Model model) {
		List<CurrencyType> currencyTypes = currencyTypeService.getAll();
		model.addAttribute("currencyTypes", currencyTypes);
		return "settings/currencyTypes/index";
	}

	@GetMapping("/currencyTypeAdd")
	public String CurrencyTypeAdd() {
		return "settings/currencyTypes/currencyTypeAdd";
	}

	@PostMapping("/saveCurrencyTypes")
	public String saveCurrencyType(@ModelAttribute(value="CurrencyType") CurrencyType currencyType) {
		currencyTypeService.saveCurrencyType(currencyType);
		return "redirect:/currencyTypesList/page";
	}


	@GetMapping("/clearCurrencyTypeSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"currencyTypeId","asc");
	}

	@GetMapping("/searchCurrencyType")
	public String searchByCurrencyType(String keyword,Model model ) {
		List<CurrencyType> currencyTypes =null;

		if  (keyword==null){

			return findPaginated(1,model,"currencyTypeId","asc");
		} else{
			currencyTypes= currencyTypeService.searchByCurrencyType(keyword);
			model.addAttribute("currencyTypes",currencyTypes);
			return "settings/currencyTypes/currencyTypes";}
	}

	@RequestMapping(value ="/currencyTypes/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteCurrencyType(@PathVariable Integer id) {
		this.currencyTypeService.deleteUsingId(id);
		return "redirect:/currencyTypesList/page";
	}

	@GetMapping("/currencyTypeEdit/{id}")
	public String editCurrencyType(@PathVariable(value="id") Integer id ,Model model){

		CurrencyType currencyType =currencyTypeService.searchCurrencyTypeById(id);
		model.addAttribute("currencyType", currencyType);
		return "settings/currencyTypes/currencyTypeEdit";
	}

	@GetMapping("/currencyTypeDetail/{id}")
	public String detailCurrencyType(@PathVariable(value="id") Integer id ,Model model){

		CurrencyType currencyType =currencyTypeService.searchCurrencyTypeById(id);
		model.addAttribute("currencyType", currencyType);
		return "settings/currencyTypes/currencyTypeDetail";
	}


	@PostMapping("/updateCurrencyType/{id}")
	public String updateCurrencyType(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("CurrencyType") CurrencyType currencyType,Model model){

		//get region from database by id
		CurrencyType existingCurrencyType= currencyTypeService.searchCurrencyTypeById(id);
		existingCurrencyType.setCurrencyTypeId(currencyType.getCurrencyTypeId());
		existingCurrencyType.setCurrencyTypeName(currencyType.getCurrencyTypeName());
		existingCurrencyType.setCurrencyTypeDescription(currencyType.getCurrencyTypeDescription());
		//save record
		currencyTypeService.updateCurrencyType(existingCurrencyType);
		return "redirect:/currencyTypesList/page";
	}
	// /page/1?sortField=name&sortDir=asc
	@GetMapping("/currencyTypesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<CurrencyType> page=currencyTypeService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<CurrencyType> currencyTypes=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("currencyTypes", currencyTypes);
		return  "settings/currencyTypes/currencyTypes";
	}

	@GetMapping("/currencyTypesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"currencyTypeId","asc");
	}

}
