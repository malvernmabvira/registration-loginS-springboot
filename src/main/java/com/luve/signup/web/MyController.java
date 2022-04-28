package com.luve.signup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luve.signup.service.UsersService;
import com.luve.signup.web.dto.UsersRegistrationDto;

@Controller
@RequestMapping("/registration")
public class MyController {
	
	private UsersService service;

	public MyController(UsersService service) {
		super();
		this.service = service;
	}
	
	@ModelAttribute("user")
	public UsersRegistrationDto userRegDto() {
		return new UsersRegistrationDto();
	}
	
	@GetMapping
	public String registration() {
		return "registration";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("user") UsersRegistrationDto regDto) {
		service.save(regDto);
		return "redirect:/registration?success";
	}

}
