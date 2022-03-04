package com.dandan.danvesting.post.comment.model;

import java.util.List;

import com.dandan.danvesting.post.comment.like.model.CommentLike;

public class CommentDetail {
	private Comment comment;
	private CommentLike commentLikeDislike;
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public CommentLike getCommentLikeDislike() {
		return commentLikeDislike;
	}
	public void setCommentLikeDislike(CommentLike commentLikeDislike) {
		this.commentLikeDislike = commentLikeDislike;
	}
	
}
