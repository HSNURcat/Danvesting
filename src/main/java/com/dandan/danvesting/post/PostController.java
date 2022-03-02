package com.dandan.danvesting.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dandan.danvesting.post.bo.PostBO;
import com.dandan.danvesting.post.model.Post;

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
	public String getPostDetail(@RequestParam("postId") int postId, Model model) {
		
		Post post = postBO.getPostDetail(postId);
		
		model.addAttribute("postDetail", post);
		
		return "post/postDetail";
	}
	
}
