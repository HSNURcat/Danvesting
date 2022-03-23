package com.dandan.danvesting.column.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.column.comment.bo.ColumnCommentBO;

@RestController
@RequestMapping("/post/column")
public class ColumnCommentRestController {
	
	@Autowired
	private ColumnCommentBO columnCommentBO;
	
	@PostMapping("create_opinion")
	public Map<String, String> createComment(
			@RequestParam("columnId") int columnId,
			@RequestParam("commentContent") String content,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		String nickName = (String)session.getAttribute("nickName");
		
		int count = columnCommentBO.addComment(userId, nickName, columnId, content);
		
		Map<String, String> result = new HashMap<>();
		
		if (count != 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		return result;
		
	}
	
}
