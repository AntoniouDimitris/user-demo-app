package com.assignment.demoapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.demoapp.entity.User;
import com.assignment.demoapp.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/manageUsers")
	public String manageUsers(Model theModel) {
		
		// get customers from the service
		List<User> theUsers = userService.getUsers();
		
		// add the customers to the model
		theModel.addAttribute("users", theUsers);
		return "manageUsers";
	}
	
	@GetMapping("/showAddUserForm")
	public String showAddUserForm(Model theModel) {
		
		User theUser = new User();
		
		theModel.addAttribute("user", theUser);
		
		return "user-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") int theId, Model theModel) {
		
		User theUser = userService.getUser(theId);
		
		theModel.addAttribute("user", theUser);
		
		return "user-form";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@Valid @ModelAttribute("user") User theUser, BindingResult theBindingResult, ModelMap model) {
		
		model.addAttribute("gender", theUser.getGender());
		
		if (theBindingResult.hasErrors()) {
			
			for (Object object : theBindingResult.getAllErrors()) {
			    if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        System.out.println(fieldError.getField() + fieldError.getCode());
			    }

			    if(object instanceof ObjectError) {
			        ObjectError objectError = (ObjectError) object;

			        System.out.println(objectError.getObjectName() + objectError.getCode());
			    }
			}
			return "user-form";
		}
		else {
			userService.saveUser(theUser);
			
			return "redirect:/users/manageUsers";
		}
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int theId) {
		
		userService.deleteUser(theId);
		
		return "redirect:/users/manageUsers";
	}
	
	 @ModelAttribute("genderList")
	  public Map<String, String> getCountryList() {
	     Map<String, String> genderList = new HashMap<String, String>();
	     genderList.put("M", "Male");
	     genderList.put("F", "Female");
	     genderList.put("O", "Other");
	     return genderList;
	  }
}
