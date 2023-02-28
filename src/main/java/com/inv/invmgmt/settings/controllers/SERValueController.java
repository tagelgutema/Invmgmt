package com.inv.invmgmt.settings.controllers;


import com.inv.invmgmt.settings.models.SERValue;
import com.inv.invmgmt.settings.services.SERValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SERValueController {
	@Autowired
	private SERValueService serValueService;

/*	@GetMapping("/settings")
	public String getIndexSERValue(Model model) {
		List<SERValue> serValues = serValueService.getAll();
		model.addAttribute("SERValue", serValues);
		return "settings/index";
	}*/

	@GetMapping("/serValueAdd")
	public String serValueAdd() {
	return "settings/serValues/serValueAdd";
	}

	@PostMapping("/saveSerValues")
	public String saveSerValue(@ModelAttribute(value="SERValue") SERValue serValue) {
		serValueService.saveSerValue(serValue);
		return "redirect:/serValuesList/page";
	}

	@GetMapping("/clearSerValueSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"serId","asc");
	}

	@GetMapping("/searchSerValue")
	public String searchBySerValue(String keyword,Model model ) {
		List<SERValue> serValues=null;

		if  (keyword==null){

			return findPaginated(1,model,"serId","asc");
		} else{
		serValues = serValueService.searchBySerValue(keyword);
		model.addAttribute("serValues",serValues);
			return "settings/serValues/serValues";}
	}

	@RequestMapping(value ="/serValues/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteSerValue(@PathVariable Integer id) {
		this.serValueService.deleteUsingId(id);
		return "redirect:/serValuesList/page";
	}

	@GetMapping("/serValueEdit/{id}")
	public String editSerValue(@PathVariable(value="id") Integer id ,Model model){

		SERValue serValue =serValueService.searchSerValueById(id);
		model.addAttribute("serValue", serValue);
		return "settings/serValues/serValueEdit";
	}

	@GetMapping("/serValueDetail/{id}")
	public String detailSerValue(@PathVariable(value="id") Integer id ,Model model){

		SERValue serValue =serValueService.searchSerValueById(id);
		model.addAttribute("serValue", serValue);
		return "settings/serValues/serValueDetail";
	}

	@PostMapping("/updateSerValues/{id}")
	public String updateSerValues(@PathVariable(value="id") Integer id ,
							   @ModelAttribute("SERValue") SERValue serValue,Model model){

		//get servalue from database by id
		SERValue existingSerValue= serValueService.searchSerValueById(id);
		existingSerValue.setSerId(serValue.getSerId());
		existingSerValue.setSerValue(serValue.getSerValue());
		existingSerValue.setSerMonth(serValue.getSerMonth());
		//save record
		serValueService.updateSerValue(existingSerValue);
		return "redirect:/serValuesList/page";
	}
	// /page/1?sortField=name&sortDir=asc
	@GetMapping("/serValuesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<SERValue> page=serValueService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<SERValue> serValues=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("serValues", serValues);
		return  "settings/serValues/serValues";
	}

	@GetMapping("/serValuesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"serId","asc");
	}



}
