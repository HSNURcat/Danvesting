package com.dandan.danvesting.model;

import java.util.List;

import com.dandan.danvesting.column.model.Column;
import com.dandan.danvesting.post.model.Post;

public class MainPageData {
	private List<Post> post;
	private List<Column> column;
	
	public List<Post> getPost() {
		return post;
	}
	public void setPost(List<Post> post) {
		this.post = post;
	}
	public List<Column> getColumn() {
		return column;
	}
	public void setColumn(List<Column> column) {
		this.column = column;
	}
}	
