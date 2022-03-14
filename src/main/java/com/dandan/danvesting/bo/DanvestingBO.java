package com.dandan.danvesting.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.column.bo.ColumnBO;
import com.dandan.danvesting.column.model.Column;
import com.dandan.danvesting.model.MainPageData;
import com.dandan.danvesting.post.bo.PostBO;
import com.dandan.danvesting.post.model.Post;

@Service
public class DanvestingBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private ColumnBO columnBO;
	
	// 자유게시판 데이터 끌어옴
	public List<Post>getPosts() {
		List<Post> posts = postBO.getPost();
		
		return posts;
	}
	
	// 칼럼 데이터 끌어옴
	public List<Column>getColumns() {
		List<Column>columns = columnBO.getColumns();
		
		return columns;
	}
	
	//메인 페이지에 띄울 데이터 생성
	public MainPageData getMainPageData() {
		List<Post> posts = this.getPosts();
		List<Column>columns = this.getColumns();
		
		MainPageData mainPageData = new MainPageData();
		
		//자유게시판 데이터 넣음
		mainPageData.setPost(posts);
		
		//칼럼 데이터 넣음
		mainPageData.setColumn(columns);
		
		return mainPageData;
	}
}
