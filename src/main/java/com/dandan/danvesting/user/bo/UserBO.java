package com.dandan.danvesting.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.common.EncryptUtils;
import com.dandan.danvesting.user.dao.UserDAO;
import com.dandan.danvesting.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String nickName) {
		String encryptedPW = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptedPW, name, nickName);
	}
	
	public User signIn(String loginId, String password) {
		String encryptedPW = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptedPW);
	}
	
	public int checkDuplication(String longinId) {
		return userDAO.selectUserByLoginId(longinId);
	}
	
	public int checkMember(int userId, String password) {
		String encryptedPW = EncryptUtils.md5(password);
		
		return userDAO.selectCheckUser(userId, encryptedPW);
	}
	
	
	public String getLoginId(int userId) {
		return userDAO.selectUserLoginId(userId);
	}
	
	public int updateUserInfo(int userId, String password, String name, String nickName) {
		String encryptedPW = EncryptUtils.md5(password);
		
		return userDAO.updateUserInfo(userId, encryptedPW, name, nickName);
	}

}
