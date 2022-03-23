package com.dandan.danvesting.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dandan.danvesting.user.bo.UserBO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@GetMapping("/sign_up_view")
	public String signUp() {
		return "user/signUp";
	}
	
	@GetMapping("/sign_in_view")
	public String signIn() {
		return "user/signIn";
	}
	
	@GetMapping("/logout")
	public String signOut(HttpServletRequest request) {
		//세션 정보 삭제
		HttpSession session = request.getSession();
		
		//세션에 회원 정보 제거
		session.removeAttribute("id");
		session.removeAttribute("loginId");
		session.removeAttribute("nickName");
		
		return "redirect:/user/sign_in_view";
	}
	
	@GetMapping("/member/check_member")
	public String checkMember(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		String loginId = userBO.getLoginId(userId);
		
		model.addAttribute("userInfo", loginId);
		
		return "user/checkMember";
	}
	
	@GetMapping("/member/change_user_info")
	public String changeUserInfo(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		String loginId = userBO.getLoginId(userId);
		
		model.addAttribute("userInfo", loginId);
		
		return "user/rewriteUserInfo";
	}
}
