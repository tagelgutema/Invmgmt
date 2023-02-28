package com.inv.invmgmt.settings.controllers;

import com.inv.invmgmt.settings.models.NXPValue;
import com.inv.invmgmt.settings.services.NXPValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NXPValueController {


	@Autowired
	private NXPValueService nxpValueService;

	@GetMapping("/nxpValues")
	public String getIndexNXPValue(Model model) {
		List<NXPValue> nxpValues = nxpValueService.getAll();
		model.addAttribute("nxpValues", nxpValues);
		return "nxpValues/index";
	}

	@GetMapping("/nxpValueAdd")
	public String nxpValueAdd() {
		return "settings/nxpValues/nxpValueAdd";
	}

	@PostMapping("/saveNxpValues")
	public String saveNXPValue(@ModelAttribute(value="NXPValue") NXPValue nxpValue) {
		nxpValueService.saveNXPValue(nxpValue);
		return "redirect:/nxpValuesList/page";
	}


	@GetMapping("/clearNxpValueSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"nxpValueId","asc");
	}

	@GetMapping("/searchNxpValue")
	public String searchByNXPValue(String keyword,Model model ) {
		List<NXPValue> nxpValues =null;

		if  (keyword==null){

			return findPaginated(1,model,"deptId","asc");
		} else{
			nxpValues= nxpValueService.searchByNXPValue(keyword);
			model.addAttribute("nxpValues",nxpValues);
			return "settings/nxpValues/nxpValues";}
	}

	@RequestMapping(value ="/nxpValues/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteNXPValue(@PathVariable Integer id) {
		this.nxpValueService.deleteUsingId(id);
		return "redirect:/nxpValuesList/page";
	}

	@GetMapping("/nxpValueEdit/{id}")
	public String editNXPValue(@PathVariable(value="id") Integer id ,Model model){

		NXPValue nxpValue =nxpValueService.searchNXPValueById(id);
		model.addAttribute("nxpValue", nxpValue);
		return "settings/nxpValues/nxpValueEdit";
	}

	@GetMapping("/nxpValueDetail/{id}")
	public String detailNXPValue(@PathVariable(value="id") Integer id ,Model model){

		NXPValue nxpValue =nxpValueService.searchNXPValueById(id);
		model.addAttribute("nxpValue", nxpValue);
		return "settings/nxpValues/nxpValueDetail";
	}


	@PostMapping("/updateNxpValue/{id}")
	public String updateNXPValue(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("NXPValue") NXPValue nxpValue,Model model){

		//get region from database by id
		NXPValue existingNxpValue= nxpValueService.searchNXPValueById(id);
		existingNxpValue.setNxpValueId(nxpValue.getNxpValueId());
		existingNxpValue.setNxpValue(nxpValue.getNxpValue());
		existingNxpValue.setNxpValueYear(nxpValue.getNxpValueYear());
		existingNxpValue.setNxpValueDescription(nxpValue.getNxpValueDescription());
		//save record
		nxpValueService.updateNXPValue(existingNxpValue);
		return "redirect:/nxpValuesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/nxpValuesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<NXPValue> page=nxpValueService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<NXPValue> nxpValues=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("nxpValues", nxpValues);
		return  "settings/nxpValues/nxpValues";
	}

	@GetMapping("/nxpValuesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"nxpValueId","asc");
	}
}
