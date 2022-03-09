package com.dandan.danvesting.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.user.bo.UserBO;
import com.dandan.danvesting.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 회원가입
	@PostMapping("/sign_up")
	public Map<String, String> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("nickName") String nickName
			) {
		
		int count = userBO.addUser(loginId, password, name, nickName);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		
		return result;
	}
	
	//로그인
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request
			) {
		
		User user = userBO.signIn(loginId, password);
		
		Map<String, String> result = new HashMap<>();
		
		if (user != null) {
			//로그인 성공
			result.put("result", "success");
			
			//세션 생성
			HttpSession session = request.getSession();
			
			//세션 정보 저장
			session.setAttribute("id", user.getId());
			session.setAttribute("loginId", user.getLoginId());
			session.setAttribute("nickName", user.getNickName());
			
		} else {
			//로그인 실패
			result.put("result", "failure");
		}
		
		return result;
	}
	
	//중복가입 방지
	@PostMapping("/check_duplication")
	public Map<String, String> checkDuplication(@RequestParam("loginId") String loginId) {
		
		int count = userBO.checkDuplication(loginId);
		
		Map<String, String> result = new HashMap<>();
		
		if (count != 0) {//중복될때
			result.put("is_duplication", "true");//true반환
		} else {
			result.put("is_duplication", "false");
		}
		return result;
	}
	
	
	//회원정보 수정전 회원확인
	@PostMapping("/member/check_member")
	public Map<String, String> checkMember(
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		int count = userBO.checkMember(userId, password);
		
		Map<String, String>result = new HashMap<>();
		
		if (count == 0) {
			result.put("result", "failure");
		} else {
			result.put("result", "success");
		}
		return result;
	}
	
	//회원정보 수정
	@PostMapping("/member/rewrite_user_info")
	public Map<String, String> rewriteMember(
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("nickName") String nickName,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("id");
		
		int count = userBO.updateUserInfo(userId, password, name, nickName);
		
		Map<String, String> result = new HashMap<>();
		if (count==0) {
			result.put("result", "failure");
		} else {
			result.put("result", "success");
		}
		return result;
	}
	
	
}
