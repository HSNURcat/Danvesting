package com.dandan.danvesting.column.comment.like.model;

import java.util.Date;

public class ColumnCommentLike {
	private int columnId;
	private int commentId;
	private int likeCount;
	private int dislikeCount;
	private boolean commentLike;//좋아요 여부
	private boolean commentDislike;//싫어요 여부
	
	
	public int getColumnId() {
		return columnId;
	}
	public void setColumnId(int columnId) {
		this.columnId = columnId;
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
