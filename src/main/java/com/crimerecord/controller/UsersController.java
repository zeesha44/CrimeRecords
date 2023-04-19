package com.crimerecord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crimerecord.Service.UsersService;
import com.crimerecord.model.Users;
import com.crimerecord.repository.UsersRepository;



@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	private UsersRepository repository;
	
	UsersController(UsersRepository repository) {
	    this.repository = repository;
	  }

	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("registerRequest", new Users());
		return "register_page";
	}
	
	@GetMapping("/login")
	public String getEmailPage(Model model) {
		model.addAttribute("loginRequest", new Users());
		return "login_page";
	}
	@GetMapping("/edituser")
	public String getEditPage(Model model) {
		model.addAttribute("editRequest", new Users());
		return "edit_users";
	}
	
//	@RequestMapping("/edituser")
//	@ResponseBody
//	public String editUser() {
//		return "edit_users";
//	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute Users usersModel) {
		System.out.println("register request: " + usersModel);
		Users registeredUser = usersService.registerUsers(usersModel.getEmail(), usersModel.getPassword());
		return registeredUser == null ? "error_page" : "dashboard";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Users usersModel, Model model) {
		System.out.println("register request: " + usersModel);
		Users authenticated = usersService.authenticate(usersModel.getEmail(), usersModel.getPassword());
		if(authenticated != null) {
			model.addAttribute("userEmail", authenticated.getEmail());
			return "dashboard";
		}
		else {
			return "dashboard";
		}
	}
	
//	@GetMapping("/users/{id}")
//	  Users one(@PathVariable Long id) {
//	    
//	    return repository.findById(User, id)
//	      .orElseThrow(() -> new UsersNotFoundException(id));
//	  }
//
//	  @PutMapping("/users/{id}")
//	  Users replaceUsers(@RequestBody Users newUsers, @PathVariable Long id) {
//	    
//	    return repository.findById(id)
//	      .map(employee -> {
//	        employee.setName(newUsers.getName());
//	        employee.setRole(newUsers.getRole());
//	        return usersRepository.save(employee);
//	      })
//	      .orElseGet(() -> {
//	        newUsers.setId(id);
//	        return usersRepository.save(newUsers);
//	      });
//	  }
//
//	  @DeleteMapping("/users/{id}")
//	  void deleteUsers(@PathVariable Long id) {
//	    repository.deleteById(id);
//	  }

}
