package com.dandan.danvesting.post.comment.like.model;

public class CommentLike {
	private int postId;
	private int commentId;
	private int likeCount;
	private int dislikeCount;
	private boolean commentLike;
	private boolean commentDislike;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	public boolean isCommentLike() {
		return commentLike;
	}
	public void setCommentLike(boolean commentLike) {
		this.commentLike = commentLike;
	}
	public boolean isCommentDislike() {
		return commentDislike;
	}
	public void setCommentDislike(boolean commentDislike) {
		this.commentDislike = commentDislike;
	}

	
	
}
