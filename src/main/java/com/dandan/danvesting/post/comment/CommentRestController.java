package com.dandan.danvesting.post.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.post.comment.bo.CommentBO;

@RestController
@RequestMapping("/post/content")
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/comment/create")
	public Map<String, String> addComment(
			@RequestParam("commentContent") String commentContent,
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		String nickName = (String)session.getAttribute("nickName");
		
		int count = commentBO.addComment(userId, postId, nickName, commentContent);
		
		Map<String, String> result = new HashMap<>();
		
		if (count != 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		return result;
	}
	
	
}
