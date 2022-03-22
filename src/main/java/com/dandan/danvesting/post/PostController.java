package com.dandan.danvesting.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dandan.danvesting.post.bo.PostBO;
import com.dandan.danvesting.post.model.Post;
import com.dandan.danvesting.post.model.PostDetail;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/content/create_post")
	public String createPost() {
		return "post/createPost";
	}
	
	@GetMapping("/content/board")
	public String getPostsList(Model model) {
		
		List<Post> posts = postBO.getPost();
		
		model.addAttribute("postList", posts);
		
		return "post/postList";
	}
	
	@GetMapping("/content/list_detail_view")
	public String getPostDetail(@RequestParam("postId") int postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		PostDetail postDetail = postBO.getPostDetail(postId, userId);
		
		model.addAttribute("postDetail", postDetail);
		
		return "post/postDetail";
	}
	
	@GetMapping("/content/rewrite_view")
	public String showPostDetail(@RequestParam("postId") int postId, Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		PostDetail postDetail = postBO.getPostDetail(postId, userId);
		
		model.addAttribute("postDetail", postDetail);
		
		return "post/rewritePost";
	}
	
}
