package com.dandan.danvesting.post;

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
	
	@PostMapping("/content/check_authority")
	public Map<String, Boolean> checkAuthority(
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		//현재 로그인된 사용자의 회원정보
		int userId = (Integer)session.getAttribute("id");
		
		Map<String, Boolean> result = new HashMap<>();
		
		//수정권한 있음(true 반환), 수정권한 없음(false 반환)
		boolean rewriteAuthority = postBO.getPostAuthor(userId, postId);
		
		if (rewriteAuthority) {
			result.put("authority", rewriteAuthority);
		} else {
			result.put("authority", rewriteAuthority);
		}
		
		return result;
	}
	
	//파일수정 없이 텍스트 수정만 있을경우
	@PostMapping("/content/rewrite")
	public Map<String, String> rewritePost(
			@RequestParam("postId") int postId,
			@RequestParam("postTitle") String postTitle,
			@RequestParam("postText") String postText,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		//현재 로그인된 사용자의 회원정보
		int userId = (Integer)session.getAttribute("id");
		
		//수정권한 있음(true 반환), 수정권한 없음(false 반환)
		boolean rewriteAuthority = postBO.getPostAuthor(userId, postId);
		
		Map<String, String> result = new HashMap<>();
		
		if (rewriteAuthority) {//수정권한 있으면, 수정진행
			int count = postBO.rewritePost(userId, postId, postTitle, postText);
			
			if (count == 1) {
				result.put("result", "success");
			} else {
				result.put("result", "failure");
			}
			
		} else {//수정권한 없으면, 권한 없음 결과값 넣음
			result.put("result", "NoAuthority");
		}
		
		return result;
	}
	
	//파일수정이 있는경우
	@PostMapping("/content/rewrite_withfile")
	public Map<String, String> rewritePostWithFile(
			@RequestParam("postId") int postId,
			@RequestParam("postTitle") String postTitle,
			@RequestParam("postText") String postText,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		//현재 로그인된 사용자의 회원정보
		int userId = (Integer)session.getAttribute("id");
		
		//수정권한 있음(true 반환), 수정권한 없음(false 반환)
		boolean rewriteAuthority = postBO.getPostAuthor(userId, postId);
		
		Map<String, String> result = new HashMap<>();
		
		if (rewriteAuthority) {//수정권한 있으면, 수정진행
			int count = postBO.rewritePostWithFile(userId, postId, postTitle, postText, file);
			
			if (count == 1) {
				result.put("result", "success");
			} else {
				result.put("result", "failure");
			}
			
		} else {//수정권한 없으면, 권한 없음 결과값 넣음
			result.put("result", "NoAuthority");
		}
		return result;
	}
	
	@GetMapping("/content/delete")
	public Map<String, String> deletePost(
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		boolean deletePost = postBO.deletePost(postId, userId);
		
		Map<String, String>result = new HashMap<>();
		
		if(deletePost) {
			result.put("result", "success");
		} else {
			result.put("result", "false");
		}
		
		return result;
	}
}
