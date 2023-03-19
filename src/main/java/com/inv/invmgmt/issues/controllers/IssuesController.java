package com.inv.invmgmt.issues.controllers;


import com.inv.invmgmt.employees.models.Employee;
import com.inv.invmgmt.employees.services.EmployeeService;
import com.inv.invmgmt.issues.model.Issue;
import com.inv.invmgmt.products.models.Product;
import com.inv.invmgmt.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.inv.invmgmt.issues.services.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IssuesController {

	@Autowired
	private IssueService issueService;

	@Autowired
	private ProductService productService ;

	@Autowired
	private EmployeeService employeeService;


	public Model addModelAttribute(Model model){


		model.addAttribute("products",productService.getAll());
		model.addAttribute("Issues",issueService.getAll());
		model.addAttribute("employees",employeeService.getAll());


		return model;
	}
	@GetMapping("/issues")
	public String getIndexIssue(Model model) {
		List<Issue> issues = issueService.getAll();
		model.addAttribute("issues", issues);

		return  "transaction/index";
	}

	@GetMapping("/issueAdd")
	public String issueAdd(Model model) {
		addModelAttribute(model);

		return "transaction/issue/issueAdd";
	}

	@PostMapping("/saveIssues")
	public String saveIssue(@ModelAttribute(value="Issue") Issue issue,@ModelAttribute(value="Product" )
	Product product) {

	 Integer  id= Integer.valueOf(issue.getProductid());
	 System.out.println(id);

		//modify
		Product existingProduct= productService.searchProductById(id);
    	/*existingProduct.setProductId(product.getProductId());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductDescription(product.getProductDescription());

		existingProduct.setProductionDate(product.getProductionDate());
		existingProduct.setPurchaseDate(product.getPurchaseDate());
		existingProduct.setProductPrice(product.getProductPrice());
		existingProduct.setAbacusNumber(product.getAbacusNumber());
		existingProduct.setSerialNumber(product.getSerialNumber());
		existingProduct.setPsmTag(product.getPsmTag());

		existingProduct.setProductCompliance(product.getProductCompliance());

		existingProduct.setCategoryid(product.getCategoryid());
		existingProduct.setVendorid(product.getVendorid());
		existingProduct.setProductstatusid(product.getProductstatusid());
		existingProduct.setSerid(product.getSerid());
		existingProduct.setCurrencytypeid(product.getCurrencytypeid());
		existingProduct.setProductModel(product.getProductModel());
		existingProduct.setProductRemark(product.getProductRemark());*/
		existingProduct.setProductCurrent("Issued");

		//save record
		issueService.saveIssue(issue);
		productService.updateProduct(existingProduct);
		return "redirect:/issuesList/page";
	}

	@GetMapping("/clearIssueSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"issueId","asc");
	}

	@GetMapping("/searchIssue")
	public String searchByIssue(String keyword,Model model ) {
		List<Issue> issues =null;

		if  (keyword==null){

			return findPaginated(1,model,"issueId","asc");
		} else{
			issues= issueService.searchByIssue(keyword);
			model.addAttribute("issues",issues);
			return "transaction/issue/issues";}
	}

	@RequestMapping(value ="/issues/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteIssue(@PathVariable Long id) {
		this.issueService.deleteUsingId(id);
		return "redirect:/issuesList/page";
	}

	@GetMapping("/issueEdit/{id}")
	public String editIssue(@PathVariable(value="id") Long id ,Model model){
		addModelAttribute(model);
		Issue issue =issueService.searchIssueById(id);
		model.addAttribute("issue", issue);
		return "transaction/issue/issueEdit";
	}

	@GetMapping("/issueDetail/{id}")
	public String detailIssue(@PathVariable(value="id") Long id ,Model model){
		addModelAttribute(model);
		Issue issue =issueService.searchIssueById(id);
		model.addAttribute("issue", issue);
		return "transaction/issue/issueDetail";
	}


	@PostMapping("/updateIssue/{id}")
	public String updateIssue(@PathVariable(value="id") Long id ,
								 @ModelAttribute("Issue") Issue issue,Model model){

		//get region from database by id
		Issue existingIssue= issueService.searchIssueById(id);
		existingIssue.setIssueId(issue.getIssueId());
		existingIssue.setProductid(issue.getProductid());

		existingIssue.setIssueDate(issue.getIssueDate());
		existingIssue.setIssuedBy(issue.getIssuedBy());
		existingIssue.setAuthorizedBy(issue.getAuthorizedBy());

		existingIssue.setRemark(issue.getRemark());

		//save record
		issueService.updateIssue(existingIssue);
		return "redirect:/issuesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/issuesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Issue> page=issueService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Issue> issues=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("issues", issues);
		return  "transaction/issue/issues";
	}

	@GetMapping("/issuesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"issueId","asc");
	}
}


