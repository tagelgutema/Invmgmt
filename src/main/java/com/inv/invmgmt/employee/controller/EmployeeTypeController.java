package com.inv.invmgmt.employee.controller;

import com.inv.invmgmt.employee.model.EmployeeType;
import com.inv.invmgmt.employee.services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeTypeController {

	@Autowired
	private EmployeeTypeService employeeTypeService;

	@GetMapping("/employeeTypes")
	public String getIndexEmployeeType(Model model) {
		List<EmployeeType> employeeTypes = employeeTypeService.getAll();
		model.addAttribute("employeeTypes", employeeTypes);
		return "employeeTypes/index";
	}

	@GetMapping("/employeeTypeAdd")
	public String employeeTypeAdd() {
		return "employees/employeeType/employeeTypeAdd";
	}

	@PostMapping("/saveEmployeeTypes")
	public String saveEmployeeType(@ModelAttribute(value="EmployeeType") EmployeeType employeeType) {
		employeeTypeService.saveEmployeeType(employeeType);
		return "redirect:/employeeTypesList/page";
	}

	@GetMapping("/clearEmployeeTypeSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"employeeTypeId","asc");
	}

	@GetMapping("/searchEmployeeType")
	public String searchByEmployeeType(String keyword,Model model ) {
		List<EmployeeType> employeeTypes =null;

		if  (keyword==null){

			return findPaginated(1,model,"employeeTypeId","asc");
		} else{
			employeeTypes= employeeTypeService.searchByEmployeeType(keyword);
			model.addAttribute("employeeTypes",employeeTypes);
			return "employees/employeeType/employeeTypes";}
	}

	@RequestMapping(value ="/employeeTypes/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteEmployeeType(@PathVariable Integer id) {
		this.employeeTypeService.deleteUsingId(id);
		return "redirect:/employeeTypesList/page";
	}

	@GetMapping("/employeeTypeEdit/{id}")
	public String editEmployeeType(@PathVariable(value="id") Integer id ,Model model){

		EmployeeType employeeType =employeeTypeService.searchEmployeeTypeById(id);
		model.addAttribute("employeeType", employeeType);
		return "employees/employeeType/employeeTypeEdit";
	}

	@GetMapping("/employeeTypeDetail/{id}")
	public String detailEmployeeType(@PathVariable(value="id") Integer id ,Model model){

		EmployeeType employeeType =employeeTypeService.searchEmployeeTypeById(id);
		model.addAttribute("employeeType", employeeType);
		return "employees/employeeType/employeeTypeDetail";
	}


	@PostMapping("/updateEmployeeType/{id}")
	public String updateEmployeeType(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("EmployeeType") EmployeeType employeeType,Model model){

		//get region from database by id
		EmployeeType existingEmployeeType= employeeTypeService.searchEmployeeTypeById(id);
		existingEmployeeType.setEmployeeTypeId(employeeType.getEmployeeTypeId());
		existingEmployeeType.setEmployeeTypeName(employeeType.getEmployeeTypeName());
		existingEmployeeType.setEmployeeTypeDescription(employeeType.getEmployeeTypeDescription());
		//save record
		employeeTypeService.updateEmployeeType(existingEmployeeType);
		return "redirect:/employeeTypesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/employeeTypesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<EmployeeType> page=employeeTypeService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<EmployeeType> employeeTypes=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("employeeTypes", employeeTypes);
		return  "employees/employeeType/employeeTypes";
	}

	@GetMapping("/employeeTypesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"employeeTypeId","asc");
	}
}

