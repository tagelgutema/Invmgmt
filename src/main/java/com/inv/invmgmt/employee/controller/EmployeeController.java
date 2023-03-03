
package com.inv.invmgmt.employee.controller;

import com.inv.invmgmt.employee.model.Employee;
import com.inv.invmgmt.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public String getIndexEmployee(Model model) {
		List<Employee> employees = employeeService.getAll();
		model.addAttribute("employees", employees);

		return  "employees/index";
	}

	@GetMapping("/employeeAdd")
	public String employeeAdd() {
		return "employees/employee/employeeAdd";
	}

	@PostMapping("/saveEmployees")
	public String saveEmployee(@ModelAttribute(value="Employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/employeesList/page";
	}

	@GetMapping("/clearEmployeeSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"employeeId","asc");
	}

	@GetMapping("/searchEmployee")
	public String searchByEmployee(String keyword,Model model ) {
		List<Employee> employees =null;

		if  (keyword==null){

			return findPaginated(1,model,"employeeId","asc");
		} else{
			employees= employeeService.searchByEmployee(keyword);
			model.addAttribute("employees",employees);
			return "employees/employee/employees";}
	}

	@RequestMapping(value ="/employees/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteEmployee(@PathVariable Integer id) {
		this.employeeService.deleteUsingId(id);
		return "redirect:/employeesList/page";
	}

	@GetMapping("/employeeEdit/{id}")
	public String editEmployee(@PathVariable(value="id") Integer id ,Model model){

		Employee employee =employeeService.searchEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees/employee/employeeEdit";
	}

	@GetMapping("/employeeDetail/{id}")
	public String detailEmployee(@PathVariable(value="id") Integer id ,Model model){

		Employee employee =employeeService.searchEmployeeById(id);
		model.addAttribute("employee", employee);
		return "employees/employee/employeeDetail";
	}


	@PostMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("Employee") Employee employee,Model model){

		//get region from database by id
		Employee existingEmployee= employeeService.searchEmployeeById(id);
		existingEmployee.setEmployeeId(employee.getEmployeeId());
		existingEmployee.setPinNo(employee.getPinNo());

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setMiddleName(employee.getMiddleName());
		existingEmployee.setLastName(employee.getLastName());

		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setPhoneNo(employee.getPhoneNo());

		existingEmployee.setAddressid(employee.getAddressid());
		existingEmployee.setJobTitle(employee.getJobTitle());
		existingEmployee.setEmployeetypeid(employee.getEmployeetypeid());

				//save record
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employeesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/employeesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Employee> page=employeeService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Employee> employees=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("employees", employees);
		return  "employees/employee/employees";
	}

	@GetMapping("/employeesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"employeeId","asc");
	}
}


