package com.dandan.danvesting.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dandan.danvesting.common.FileManageService;
import com.dandan.danvesting.post.dao.PostDAO;
import com.dandan.danvesting.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int createPost(int userId, String nickName, String postTitle, String postText, MultipartFile file) {
		String filePath = FileManageService.saveFile(userId, file);
		return postDAO.insertPost(userId, nickName, postTitle, postText, filePath);
	}
	
	public List<Post> getPost() {
		return postDAO.getPost();
	}
	
	public Post getPostDetail(int postId) {
		return postDAO.getPostDetail(postId);
	}
}
