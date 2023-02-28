package com.inv.invmgmt.settings.controllers;

import com.inv.invmgmt.settings.models.Region;
import com.inv.invmgmt.settings.services.RegionServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.System.exit;

@Controller
public class RegionController {
	@Autowired
	private RegionServiceImpl regionService;

	@GetMapping("/settings")
	public String getIndexRegion(Model model) {
		List<Region> regions = regionService.getAll();
		model.addAttribute("region", regions);
		return "settings/index";
	}

	@GetMapping("/regionAdd")
	public String regionAdd() {
	return "settings/regionAdd";
	}

	@PostMapping("/saveRegions")
	public String saveRegion(@ModelAttribute(value="Region") Region region) {
		regionService.saveRegion(region);
		return "redirect:/regionsList/page";
	}
	/*@GetMapping("/regionsList")
	public String getAll(Model model){
		List <Region> regions=regionService.getAll();
		model.addAttribute("regions",regions);
		return "settings/regions";
	}
*/

	@GetMapping("/clearRegionSearch")
	public String clearSearch(Model model){

		return findPaginated(1,model,"regionId","asc");
	}

	@GetMapping("/searchRegion")
	public String searchByRegions(String keyword,Model model ) {
		List<Region> regions=null;

		if  (keyword==null){

			return findPaginated(1,model,"regionId","asc");
		} else{
		regions= regionService.searchByRegion(keyword);
		model.addAttribute("regions",regions);
			return "settings/regions";}
	}

	@RequestMapping(value ="/regions/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String deleteRegion(@PathVariable Integer id) {
		this.regionService.deleteUsingId(id);
		return "redirect:/regionsList/page";
	}

	@GetMapping("/regionEdit/{id}")
	public String editRegion(@PathVariable(value="id") Integer id ,Model model){

		Region region =regionService.searchRegionById(id);
		model.addAttribute("region", region);
		return "settings/regionEdit";
	}

	@GetMapping("/regionDetail/{id}")
	public String detailRegion(@PathVariable(value="id") Integer id ,Model model){

		Region region =regionService.searchRegionById(id);
		model.addAttribute("region", region);
		return "settings/regionDetail";
	}


	@PostMapping("/updateRegion/{id}")
	public String updateRegion(@PathVariable(value="id") Integer id ,
							   @ModelAttribute("region") Region region,Model model){

		//get region from database by id
		Region existingRegion= regionService.searchRegionById(id);
		existingRegion.setRegionId(region.getRegionId());
		existingRegion.setRegionName(region.getRegionName());
		existingRegion.setRegionDescription(region.getRegionDescription());
		//save record
		regionService.updateRegion(existingRegion);
		return "redirect:/regionsList/page";
	}
	// /page/1?sortFied=name&sortDir=asc
	@GetMapping("/regionsList/page/{pageNo}")
	public String findPaginated(@PathVariable (value="pageNo") int pageNo,
								Model model,
								@RequestParam("sortField") String sortField,
								@RequestParam("sortDir") String sortDir){

		int pageSize=5;
		Page<Region> page=regionService.findPaginated(pageNo,pageSize,sortField,sortField);
		List<Region> regions=page.getContent();
		model.addAttribute("currentPage",pageNo);
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());

		model.addAttribute("sortField",sortField);
		model.addAttribute("sortDir",sortDir);
		model.addAttribute("toggleSortDir",sortDir.equals("asc")? "desc":"asc");

		model.addAttribute("regions", regions);
		return  "settings/regions";
	}

	@GetMapping("/regionsList/page")
	public String getAllPages(Model model ){
		return findPaginated(1,model,"regionId","asc");
	}



}
