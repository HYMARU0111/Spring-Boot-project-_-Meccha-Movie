package com.infosiatec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosiatec.config.auth.PrincipalDetail;
import com.infosiatec.model.User;
import com.infosiatec.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping("auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("auth/loginForm")
	public String loginForm(String error, String exception, Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		
		return "user/loginForm";
	}
	
	@GetMapping("user/{id}/updateForm")
	public String updateForm(@PathVariable int id) {
		return"user/updateForm";
	}
	

}
