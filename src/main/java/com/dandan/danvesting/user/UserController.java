package com.dandan.danvesting.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		//세션 정보 삭제
		HttpSession session = request.getSession();
		
		//세션에 회원 정보 제거
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
	}
}
