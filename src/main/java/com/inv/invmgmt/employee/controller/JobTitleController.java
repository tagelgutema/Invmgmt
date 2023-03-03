package com.inv.invmgmt.employee.controller;

import com.inv.invmgmt.employee.model.JobTitle;
import com.inv.invmgmt.employee.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobTitleController {

	@Autowired
	private JobTitleService jobTitleService;

	@GetMapping("/jobTitles")
	public String getIndexJobTitle(Model model) {
		List<JobTitle> jobTitles = jobTitleService.getAll();
		model.addAttribute("jobTitles", jobTitles);
		return "jobTitles/index";
	}

	@GetMapping("/jobTitleAdd")
	public String jobTitleAdd() {
		return "employees/jobTitle/jobTitleAdd";
	}

	@PostMapping("/saveJobTitles")
	public String saveJobTitle(@ModelAttribute(value="JobTitle") JobTitle jobTitle) {
		jobTitleService.saveJobTitle(jobTitle);
		return "redirect:/jobTitlesList/page";
	}

	@GetMapping("/clearJobTitleSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"jobTitleId","asc");
	}

	@GetMapping("/searchJobTitle")
	public String searchByJobTitle(String keyword,Model model ) {
		List<JobTitle> jobTitles =null;

		if  (keyword==null){

			return findPaginated(1,model,"jobTitleId","asc");
		} else{
			jobTitles= jobTitleService.searchByJobTitle(keyword);
			model.addAttribute("jobTitles",jobTitles);
			return "employees/jobTitle/jobTitles";}
	}

	@RequestMapping(value ="/jobTitles/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteJobTitle(@PathVariable Integer id) {
		this.jobTitleService.deleteUsingId(id);
		return "redirect:/jobTitlesList/page";
	}

	@GetMapping("/jobTitleEdit/{id}")
	public String editJobTitle(@PathVariable(value="id") Integer id ,Model model){

		JobTitle jobTitle =jobTitleService.searchJobTitleById(id);
		model.addAttribute("jobTitle", jobTitle);
		return "jobTitles/jobTitleEdit";
	}

	@GetMapping("/jobTitleDetail/{id}")
	public String detailJobTitle(@PathVariable(value="id") Integer id ,Model model){

		JobTitle jobTitle =jobTitleService.searchJobTitleById(id);
		model.addAttribute("jobTitle", jobTitle);
		return "employees/jobTitle/jobTitleDetail";
	}


	@PostMapping("/updateJobTitle/{id}")
	public String updateJobTitle(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("JobTitle") JobTitle jobTitle,Model model){

		//get region from database by id
		JobTitle existingJobTitle= jobTitleService.searchJobTitleById(id);
		existingJobTitle.setJobTitleId(jobTitle.getJobTitleId());
		existingJobTitle.setJobTitleName(jobTitle.getJobTitleName());
		existingJobTitle.setJobTitleDescription(jobTitle.getJobTitleDescription());
		//save record
		jobTitleService.updateJobTitle(existingJobTitle);
		return "redirect:/jobTitlesList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/jobTitlesList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<JobTitle> page=jobTitleService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<JobTitle> jobTitles=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("jobTitles", jobTitles);
		return  "employees/jobTitle/jobTitles";
	}

	@GetMapping("/jobTitlesList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"jobTitleId","asc");
	}
}
