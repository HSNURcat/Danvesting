package com.dandan.danvesting.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	public int insertPost(@Param("userId") int UserId,
			@Param("nickName") String nickName,
			@Param("postText") String postText,
			@Param("filePath") String filePath); 
	
}
