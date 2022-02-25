package com.dandan.danvesting.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dandan.danvesting.common.FileManageService;
import com.dandan.danvesting.post.dao.PostDAO;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	public int createPost(int userId, String nickName, String postText, MultipartFile file) {
		String filePath = FileManageService.saveFile(userId, file);
		return postDAO.insertPost(userId, nickName, postText, filePath);
	}
}
