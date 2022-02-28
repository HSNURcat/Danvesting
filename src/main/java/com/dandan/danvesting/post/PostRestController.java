package com.dandan.danvesting.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dandan.danvesting.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/content/create")
	public Map<String, String> createPost(
			@RequestParam("postTitle") String postTitle,
			@RequestParam("postText") String postText,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		//현재 로그인된 사용자의 회원정보
		int userId = (Integer)session.getAttribute("id");
		String nickName = (String)session.getAttribute("nickName");
		
		int count = postBO.createPost(userId, nickName, postTitle, postText, file);
		
		Map<String, String> result = new HashMap<>();
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		return result;
	}
	
}
