package com.crimerecord.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.crimerecord.model.Criminals;
import com.crimerecord.model.Users;
import com.crimerecord.repository.CriminalsRepository;


@Controller
//@RequestMapping("/login")
public class CriminalsController {

	@Autowired 
	private CriminalsRepository criminalsRepository;
	
	//@Autowired
	//CriminalsService criminalsService;
	@GetMapping("/saveCriminals")
	public String getSaveCriminalsPage(Model model) {
		model.addAttribute("registerRequest", new Criminals());
		return "register_criminals";
	}
	
	@RequestMapping("/saveCriminals")
	@ResponseBody
	public ResponseEntity<String> saveCriminals(@RequestBody List<Criminals> criminalsData){
		criminalsRepository.saveAll(criminalsData);
		//return "register_criminals";
		return ResponseEntity.ok("saved");	
		
	}
	@GetMapping("/index")
	//@ResponseBody
	public ModelAndView criminals(){
		//return criminalsRepository.findByState(state);
		ModelAndView mv = new ModelAndView("index");
		//List<Criminals> criminal = criminalsRepository.findAll();
		mv.addObject("criminals", criminalsRepository.findAll());
		return mv;
	}
	
	@GetMapping("/editcriminals")
	public String getEditPage(Model model) {
		model.addAttribute("editRequest", new Users());
		return "edit_criminals";
	}
	@RequestMapping("/editcriminals")
	@ResponseBody
	public String editCriminal() {
		return "edit_criminals";
	}
	
//	@GetMapping("/index/state")
//	public ResponseEntity<List<Criminals>> getCriminalsByState(@RequestParam String state){
//		return new ResponseEntity<List<Criminals>>(criminalsRepository.findByState(state), HttpStatus.OK);
//		
//	}
	
//	@GetMapping("/index")
//	public String getCriminals(Model model) {
//		List<Criminals> criminals = criminalsService.findCriminalsByState(criminals.getState());
//		model.addAttribute("registerRequest", new Criminals());
//		return "index";
//	}
	
//	@PostMapping("/register_criminals.html")
//	public String register(@ModelAttribute Criminals criminalModel) {
//		System.out.println("register request: " + criminalModel);
//		//Users registeredUser = usersService.registerUsers(usersModel.getEmail(), usersModel.getPassword());
//		return "register_criminals.html";
//	}
//	@PostMapping("/saveCriminals")
//	public String saveStudent(@RequestBody List<Criminals> criminalsData){
//		criminalsRepository.saveAll(criminalsData);
//		return "register_criminals";
//		
//	}
}
