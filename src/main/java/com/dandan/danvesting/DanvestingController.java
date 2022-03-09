package com.dandan.danvesting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dandan.danvesting.bo.DanvestingBO;
import com.dandan.danvesting.post.model.Post;

@Controller
public class DanvestingController {
	
	@Autowired
	private DanvestingBO danvestingBO;
	
	@GetMapping("/main")
	public String mainPage(Model model) {
		
		List<Post> posts = danvestingBO.getPosts();
		
		model.addAttribute("postList", posts);
		
		return "main";
	}
}
