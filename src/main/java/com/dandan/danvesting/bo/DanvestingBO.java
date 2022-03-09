package com.dandan.danvesting.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.bo.PostBO;
import com.dandan.danvesting.post.model.Post;

@Service
public class DanvestingBO {
	
	@Autowired
	private PostBO postBO;
	
	public List<Post>getPosts() {
		List<Post> posts = postBO.getPost();
		
		return posts;
	}
}
