package com.crimerecord.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crimerecord.Service.CriminalsServiceImp;
import com.crimerecord.exception.RecordNotFoundException;
import com.crimerecord.model.Criminals;
import com.crimerecord.model.Criminals;
import com.crimerecord.repository.CriminalsRepository;


@Controller
//@RequestMapping("/login")
public class CriminalsController {

	@Autowired 
	private CriminalsRepository criminalsRepository;
	
	@Autowired
	private CriminalsServiceImp criminalsService;
	
	@GetMapping("/saveCriminals")
	public String getSaveCriminalsPage(Model model) {
		model.addAttribute("registerRequest", new Criminals());
		return "register_criminals";
	}
	
//	@PostMapping("/saveCriminals")
//	//@ResponseBody
//	public String saveCriminals(@RequestBody List<Criminals> criminalsData){
//		criminalsRepository.saveAll(criminalsData);
//		return "register_criminals";
//		//return ResponseEntity.ok("saved");	
//		
//	}
//	@RequestMapping("/search")
//	@ResponseBody
//	public ModelAndView findCriminals(@RequestParam(value = "state") String state){
//		//return criminalsRepository.findByState(state);
//		ModelAndView mv = new ModelAndView("search_results");
//		List<Criminals> criminal = criminalsRepository.findByState(state);
//		mv.addObject(criminal);
//		return mv;
//	}
//	
	@GetMapping("/search")
	public String search(String keyword, Model model) {
		return searchByPage(keyword, model, 1);
	}
	
	@GetMapping("/search/page/{pageNum}")
	private String searchByPage(String keyword, Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Criminals> result = criminalsService.search(keyword, pageNum);
		List<Criminals> listResult = result.getContent();
		
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("totalItems", result.getTotalElements());
		model.addAttribute("currentPages", pageNum);
		
		long startCount = (pageNum - 1) * CriminalsServiceImp.SEARCH_RESULT_PER_PAGE + 1;
		model.addAttribute("startCount", startCount);
		
		long endCount = startCount + CriminalsServiceImp.SEARCH_RESULT_PER_PAGE - 1;
		if (endCount > result.getTotalElements()) {
			endCount = result.getTotalElements();
		}
		model.addAttribute("endCount", endCount);
		model.addAttribute("listResult", listResult);
		model.addAttribute("keyword", keyword);
		return "search_results";
	}

//	@GetMapping("/editcriminals")
//	public String getEditPage(Model model) {
//		model.addAttribute("editRequest", new Criminals());
//		return "edit_criminals";
//	}
	@GetMapping("/editcriminals")
	//@ResponseBody
	public String editCriminal(Model model) {
			List<Criminals> criminals = criminalsService.findAllCriminals();
			model.addAttribute("criminals", criminals);
		return "edit_criminals";
	}
	
//	//postman
//	//localhost:8080/index/state?state=kaduna
//	@GetMapping("/index/state")
//	public ResponseEntity<List<Criminals>> getCriminalsByState(@RequestParam String state){
//		return new ResponseEntity<List<Criminals>>(criminalsRepository.findByState(state), HttpStatus.OK);
//		
//	}
//	

		@GetMapping("/dashboard")
	public String getCriminals(Model model) {
		List<Criminals> criminals = criminalsService.findAllCriminals();
		model.addAttribute("criminals", criminals);
		return "dashboard";
	}
	@PostMapping("/saveCriminals")
	public String register(@ModelAttribute Criminals criminalModel) {
		System.out.println("register request: " + criminalModel);
		Criminals registeredCriminals = criminalsService.registerCriminals(criminalModel.getFirstName(), criminalModel.getLastName(),
				criminalModel.getOffence(), criminalModel.getSentence(), criminalModel.getState(), criminalModel.getStatus());
		return "register_criminals";
	}
	
	@RequestMapping(path = {"/editC", "/editC{id}"})
	public String editCriminalsById(Model model, @PathVariable("id") Optional<Integer>id) throws RecordNotFoundException{
		System.out.println("editCriminalsById" + id);
		if(id.isPresent()) {
			Criminals criminal = criminalsService.getCriminalById(id.get());
			model.addAttribute("criminal", criminal);
		}
		else {
			model.addAttribute("criminal", new Criminals());
		}
		return "edit_criminals";
	}
	@RequestMapping(path = "/editC", method = RequestMethod.POST)
	public String UpdateCriminals(Criminals criminals) 
	{
		System.out.println("createOrUpdateEmployee ");
		
		criminalsService.UpdateCriminal(criminals);
		
		return "redirect:/";
	}
	@RequestMapping(path = "/deleteC/{id}")
	public String deleteCriminalsById(Model model, @PathVariable("id") Integer id) 
							throws RecordNotFoundException 
	{
		
		System.out.println("deleteCriminalsById" + id);
		
		criminalsService.deleteCriminal(id);
		return "edit_criminals";
	}
//	@PostMapping("/saveCriminals")
//	public String saveStudent(@RequestBody List<Criminals> criminalsData){
//		criminalsRepository.saveAll(criminalsData);
//		return "register_criminals";
//		
//	}
}
