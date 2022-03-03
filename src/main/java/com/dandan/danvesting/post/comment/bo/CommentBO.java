package com.dandan.danvesting.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.comment.dao.CommentDAO;
import com.dandan.danvesting.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int addComment(int userId, int postId, String nickName, String commentContent) {
		return commentDAO.insertComment(userId, postId, nickName, commentContent);
	}
	
	public List<Comment> getComments(int postId) {
		return commentDAO.selectComments(postId);
	}
}
