package com.dandan.danvesting.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	//암호화 메소드
	public static String md5(String message) {
		String encodingData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] bytes = message.getBytes(); //메시지(문자열)을 바이트 형식으로 변환
			md.update(bytes); //메시지 암호화
			
			byte[] encryptedMessage = md.digest(); //메시지 암호화 결과값 가져와서 저장
			
			//encryptedMessage를 16진수 16과 AND연산을 하고, 결과값을 16진수로 변환 
			// -> String형식의 16진수 반환받아서 encodingData에 저장 
			for (int i = 0; i < encryptedMessage.length; i++) {
				encodingData += Integer.toHexString(encryptedMessage[i] & 0xff);
				//digiest[i] & 0xff
				//ex) 00101000 & 11111111 --> 00101000
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return encodingData;
	}
}
