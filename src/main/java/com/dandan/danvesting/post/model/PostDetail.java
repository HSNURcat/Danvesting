package com.dandan.danvesting.post.model;

import java.util.List;

import com.dandan.danvesting.post.comment.like.model.CommentLike;
import com.dandan.danvesting.post.comment.model.Comment;
import com.dandan.danvesting.post.comment.model.CommentDetail;

public class PostDetail {
	private Post post;//post테이블 컬럼들
	private int postLikeCount;//게시물 좋아요 데이터
	private int postDislikeCount;//게시물 좋아요 데이터
	private boolean postLike; //게시물 좋아요 여부
	private boolean postDislike; //게시물 싫어요 여부
	private List<CommentDetail> commentDetailList;//comment테이블 컬럼들 + 댓글 좋아요/싫어요
	
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
	public boolean isPostLike() {
		return postLike;
	}
	public void setPostLike(boolean postLike) {
		this.postLike = postLike;
	}
	public boolean isPostDislike() {
		return postDislike;
	}
	public void setPostDislike(boolean postDislike) {
		this.postDislike = postDislike;
	}
	public List<CommentDetail> getCommentDetailList() {
		return commentDetailList;
	}
	public void setCommentDetailList(List<CommentDetail> commentDetailList) {
		this.commentDetailList = commentDetailList;
	}
	
}
