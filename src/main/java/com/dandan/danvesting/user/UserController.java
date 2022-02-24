package com.dandan.danvesting.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/sign_up_view")
	public String signUp() {
		return "user/signUp";
	}
	
	@GetMapping("/sign_in_view")
	public String signIn() {
		return "user/signIn";
	}
}
