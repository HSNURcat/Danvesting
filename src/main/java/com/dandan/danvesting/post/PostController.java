package com.dandan.danvesting.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post/content")
public class PostController {
	
	@GetMapping("/create_post")
	public String createPost() {
		return "post/createPost";
	}
}
