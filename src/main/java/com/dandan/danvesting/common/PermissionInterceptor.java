package com.dandan.danvesting.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor {
	//로그 클래스
	private static Logger logger = LoggerFactory.getLogger(FileManageService.class);
	
	//request가 올때 가로챔
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		
		//로그인정보 세션 생성
		HttpSession session = request.getSession();
		
		//현재 사용자가 요청한 url의 path(uri)를 가져옴 ex) /user/sign_in_view
		String uri = request.getRequestURI();
				
		// 로그인 상태일때
		if (session.getAttribute("id") != null) {
			//로그인 화면과, 회원가입 화면 접근 못하게 하고
			//리스트 화면으로 이동시키도록 해야함
			
			//이미 로그인 한 사람이 uri가 /user/sign으로 시작하는 페이지로 접근하는 것을 막음 
			if (uri.startsWith("/user/sign")) {//uri가 "/user/sign"로 시작하면
				//메인 페이지로 이동
				response.sendRedirect("/main");
				
				//원래 인터셉터는 잠깐 가로채서 처리한 이후에, 본래 사용자가 요청한 uri로 보내버린다.
				//그것을 방지하기 위해서 false를 리턴하도록 해준다.
				return false;
			}
			
//			//이미 로그인 한 사람이 아래의 uri로 시작하는 페이지로 접근하는 것을 막음 
//			if (uri.startsWith("/indices/") || uri.startsWith("/commodities/") || uri.startsWith("/economic-calendar/") || uri.startsWith("/equities/")
//					|| uri.startsWith("/rates-bonds/") || uri.startsWith("/currencies/") || uri.startsWith("/etfs/")) {
//				//not_found 페이지로 이동
//				response.sendRedirect("/not_found"); //사용시 com.dandan.danvesting/DanvestingController주석해제하기
//				
//				//원래 인터셉터는 잠깐 가로채서 처리한 이후에, 본래 사용자가 요청한 uri로 보내버린다.
//				//그것을 방지하기 위해서 false를 리턴하도록 해준다.
//				return false;
//			}
			
		} else { //비로그인 상태일때
			//비로그인 사용자가 리스트화면, 디테일 화면으로 이동하려고 요청하면
			//로그인 페이지로 이동시키도록 해야함
			
			//로그인 안한 사람이 아래의 uri로 시작하는 페이지로 접근하는 것을 막음
			if (uri.startsWith("/post/") || uri.startsWith("/user/member/") || uri.startsWith("/main")) {//uri가 "/post/" 또는 "/user/member/"로 시작하면
				//로그인 페이지로 이동
				response.sendRedirect("/user/sign_in_view");
						
				//원래 인터셉터는 잠깐 가로채서 처리한 이후에, 본래 사용자가 요청한 uri로 보내버린다.
				//그것을 방지하기 위해서 false를 리턴하도록 해준다.
				return false;
			}
		}
				
		//위의 조건문들에 다 안걸리고 왔으면, true를 반환해서 인터셉터를 통과한 이후에 할 일 하도록 처리
		return true;
		
	}
	
	
	//response 처리할때 가로챔
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) {
		//ModelAndView : 세팅해놓은 model의 정보와 jsp경로를 포함하고 있는 객체
		
	}
		
	//모든것이 완료되었을때 가로챔
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		
	}
	
}
