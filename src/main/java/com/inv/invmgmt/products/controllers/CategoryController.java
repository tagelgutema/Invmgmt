package com.inv.invmgmt.products.controllers;

import com.inv.invmgmt.products.models.Category;
import com.inv.invmgmt.products.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categorys")
	public String getIndexCategory(Model model) {
		List<Category> categorys = categoryService.getAll();
		model.addAttribute("categorys", categorys);
		return "products/category/index";
	}

	@GetMapping("/categoryAdd")
	public String categoryAdd() {
		return "products/category/categoryAdd";
	}

	@PostMapping("/saveCategorys")
	public String saveCategory(@ModelAttribute(value="Category") Category category) {
		categoryService.saveCategory(category);
		return "redirect:/categoryList/page";
	}

	@GetMapping("/clearCategorySearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"categoryId","asc");
	}

	@GetMapping("/searchCategory")
	public String searchByCategory(String keyword,Model model ) {
		List<Category> categorys =null;

		if  (keyword==null){

			return findPaginated(1,model,"categoryId","asc");
		} else{
			categorys= categoryService.searchByCategory(keyword);
			model.addAttribute("categorys",categorys);
			return "products/category/categories";}
	}

	@RequestMapping(value ="/categorys/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteCategory(@PathVariable Integer id) {
		this.categoryService.deleteUsingId(id);
		return "redirect:/categorysList/page";
	}

	@GetMapping("/categoryEdit/{id}")
	public String editCategory(@PathVariable(value="id") Integer id ,Model model){

		Category category =categoryService.searchCategoryById(id);
		model.addAttribute("category", category);
		return "products/category/categoryEdit";
	}

	@GetMapping("/categoryDetail/{id}")
	public String detailCategory(@PathVariable(value="id") Integer id ,Model model){

		Category category =categoryService.searchCategoryById(id);
		model.addAttribute("category", category);
		return "products/category/categoryDetail";
	}


	@PostMapping("/updateCategory/{id}")
	public String updateCategory(@PathVariable(value="id") Integer id ,
								   @ModelAttribute("Category") Category category,Model model){

		//get region from database by id
		Category existingCategory= categoryService.searchCategoryById(id);
		existingCategory.setCategoryId(category.getCategoryId());
		existingCategory.setCategoryName(category.getCategoryName());
		existingCategory.setCategoryDescription(category.getCategoryDescription());
		//save record
		categoryService.updateCategory(existingCategory);
		return "redirect:/categorysList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/categorysList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Category> page=categoryService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Category> categorys=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("categorys", categorys);
		return  "products/category/categories";
	}

	@GetMapping("/categorysList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"categoryId","asc");
	}
}
