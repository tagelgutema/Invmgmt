package com.inv.invmgmt.settings.controllers;

import com.inv.invmgmt.settings.models.Department;
import com.inv.invmgmt.settings.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/departments")
	public String getIndexDepartment(Model model) {
		List<Department> departments = departmentService.getAll();
		model.addAttribute("departments", departments);
		return "settings/departments/index";
	}

	@GetMapping("/departmentAdd")
	public String departmentAdd() {
		return "settings/departments/departmentAdd";
	}

	@PostMapping("/saveDepartments")
	public String saveDepartment(@ModelAttribute(value="Department") Department department) {
		departmentService.saveDepartment(department);
		return "redirect:/departmentsList/page";
	}

	@GetMapping("/clearDepartmentSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"deptId","asc");
	}

	@GetMapping("/searchDepartment")
	public String searchByDepartment(String keyword,Model model ) {
		List<Department> departments =null;

		if  (keyword==null){

			return findPaginated(1,model,"deptId","asc");
		} else{
			departments= departmentService.searchByDepartment(keyword);
			model.addAttribute("departments",departments);
			return "settings/departments/departments";}
	}

	@RequestMapping(value ="/departments/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteDepartment(@PathVariable Integer id) {
		this.departmentService.deleteUsingId(id);
		return "redirect:/departmentsList/page";
	}

	@GetMapping("/departmentEdit/{id}")
	public String editDepartment(@PathVariable(value="id") Integer id ,Model model){

		Department department =departmentService.searchDepartmentById(id);
		model.addAttribute("department", department);
		return "settings/departments/departmentEdit";
	}

	@GetMapping("/departmentDetail/{id}")
	public String detailDepartment(@PathVariable(value="id") Integer id ,Model model){

		Department department =departmentService.searchDepartmentById(id);
		model.addAttribute("department", department);
		return "settings/departments/departmentDetail";
	}


	@PostMapping("/updateDepartment/{id}")
	public String updateDepartment(@PathVariable(value="id") Integer id ,
							   @ModelAttribute("Department") Department department,Model model){

		//get region from database by id
		Department existingDepartment= departmentService.searchDepartmentById(id);
		existingDepartment.setDeptId(department.getDeptId());
		existingDepartment.setDeptName(department.getDeptName());
		existingDepartment.setDeptDescription(department.getDeptDescription());
		//save record
		departmentService.updateDepartment(existingDepartment);
		return "redirect:/departmentsList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/departmentsList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Department> page=departmentService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Department> departments=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("departments", departments);
		return  "settings/departments/departments";
	}

	@GetMapping("/departmentsList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"deptId","asc");
	}
}
