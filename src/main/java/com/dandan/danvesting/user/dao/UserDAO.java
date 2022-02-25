package com.dandan.danvesting.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.user.model.User;

@Repository
public interface UserDAO {
	
	public int insertUser(@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("nickName")String nickName);
	
	public User selectUser(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
}
