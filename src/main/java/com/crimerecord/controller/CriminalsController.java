package com.crimerecord.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimerecord.Service.CriminalsService;
import com.crimerecord.model.Criminals;
import com.crimerecord.repository.CriminalsRepository;


@RestController
//@RequestMapping("/login")
public class CriminalsController {

	@Autowired 
	private CriminalsRepository criminalsRepository;
	
	//@Autowired
	//CriminalsService criminalsService;
	
	@PostMapping("/login/saveCriminals")
	public ResponseEntity<String> saveCriminals(@RequestBody List<Criminals> criminalsData){
		criminalsRepository.saveAll(criminalsData);
		//return "saveCriminals";
		return ResponseEntity.ok("saved");	
		
	}
	@GetMapping("/index/state")
	public ResponseEntity<List<Criminals>> getCriminalsByState(@RequestParam String state){
		return new ResponseEntity<List<Criminals>>(criminalsRepository.findByState(state), HttpStatus.OK);
		
	}
	
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
