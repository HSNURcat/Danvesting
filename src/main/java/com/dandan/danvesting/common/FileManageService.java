package com.dandan.danvesting.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManageService {
	public static final String FILE_UPLOAD_PATH = "D:\\강의\\메가6개월\\springProject(danvesting)\\upload\\images/";
	
	//로그 클래스
		private static Logger logger = LoggerFactory.getLogger(FileManageService.class);
		
		//파일 저장
		public static String saveFile(int userId, MultipartFile file) {
			//파일 경로
			//사용자 별로 구분 할 수 있도록
			//사용자가 파일을 올린 시간으로 구분
			// /12_33922395/test.png
			// /{userId}_{파일 올린 시간}/{파일이름}
			
			// {userId}_{파일 올린 시간}/
			//1970년 1월 1일 기준으로 현제 몇 밀리초가 지났는지 나타내는 메소드
			String directoryName = userId + "_" + System.currentTimeMillis() + "/"; 
			
			// {폴더이름}/{userId}_{파일 올린 시간}/
			String filePath = FILE_UPLOAD_PATH + directoryName;
			
			//디렉토리 생성
			File directory = new File(filePath);
			if (directory.mkdir() == false) {
				//디렉토리 생성 에러
				logger.error("FileManagerService :: saveFile -- 디렉토리 생성");
				return null;
			}
			
			try {
				byte[] bytes = file.getBytes();
				
				//path = 파일 저장 경로 + 파일 이름
				Path path = Paths.get(filePath + file.getOriginalFilename());

				//파일 저장
				Files.write(path, bytes);
				
			} catch (IOException e) {
				// 파일 저장 실패
				logger.error("FileManagerService :: saveFile -- 파일 저장 에러");
				e.printStackTrace();
				return null;
			}
			
			//파일 접근 가능한 주소 리턴
			//<img src="/images/12_12345352/test.png>
			return "/images/" + directoryName + file.getOriginalFilename(); 
		}
		
		//파일 삭제
		public static void removeFile(String filePath) {
			
			if((filePath == null) || (filePath == "null")) {
				logger.error("FileManagerService :: saveFile -- 삭제할 파일 없음");
				return;
			}
			
			//삭제할 파일 경로
			//filePath : /images/3_1564796146351/test.png
			//실재 파일 경로 : "D:\\강의\\메가6개월\\springProject(danstagram)\\upload\\images\\3_1564796146351/test.png"
			String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
			
			//파일 지우기
			Path path = Paths.get(realFilePath);
			
			//파일이 있는지 확인
			if (Files.exists(path)) {
				//파일이 존재하면 삭제
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.error("FileManagerService :: saveFile -- 파일 삭제 실패");
					e.printStackTrace();
				}
			}
			
			//디렉토리 삭제(폴더)
			//실제 디렉토리 경로 : "D:\\강의\\메가6개월\\springProject(danstagram)\\upload\\images\\3_1564796146351
			// 실제 디렉토리 경로를 돌려주는 메소드 path.getParent();
			
			path = path.getParent();
			
			if (Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					logger.error("FileManagerService :: saveFile -- 디렉토리 삭제 실패");
					e.printStackTrace();
				}
			}
		}
}
