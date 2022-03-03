package com.dandan.danvesting.post.model;

import java.util.List;

import com.dandan.danvesting.post.comment.model.Comment;

public class PostDetail {
	private Post post;//post테이블 컬럼들
	private int postLikeCount;//게시물 좋아요 데이터
	private int postDislikeCount;//게시물 좋아요 데이터
	private List<Comment> comment;//comment테이블 컬럼들
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public int getPostLikeCount() {
		return postLikeCount;
	}
	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	public int getPostDislikeCount() {
		return postDislikeCount;
	}
	public void setPostDislikeCount(int postDislikeCount) {
		this.postDislikeCount = postDislikeCount;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
}
