package com.dandan.danvesting.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public int insertComment(
			@Param("userId") int userId,
			@Param("postId") int postId, 
			@Param("nickName") String nickName, 
			@Param("content") String commentContent);
	
	public List<Comment> selectComments(@Param("postId") int postId);
}
