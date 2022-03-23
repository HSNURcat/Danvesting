package com.dandan.danvesting.column.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.column.comment.model.ColumnComment;

@Repository
public interface ColumnCommentDAO {
	public int insertComment(
			@Param("userId") int userId, 
			@Param("nickName") String nickName, 
			@Param("columnId") int columnId, 
			@Param("content") String content);
	
	public List<ColumnComment> selectComments(@Param("columnId") int columnId);
}
