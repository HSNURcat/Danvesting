package com.dandan.danvesting.column.comment.model;

import com.dandan.danvesting.column.comment.like.model.ColumnCommentLike;

public class ColumnCommentDetail {
	private ColumnComment columnComment;
	private ColumnCommentLike columnCommentLike;
	
	public ColumnComment getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(ColumnComment columnComment) {
		this.columnComment = columnComment;
	}
	public ColumnCommentLike getColumnCommentLike() {
		return columnCommentLike;
	}
	public void setColumnCommentLike(ColumnCommentLike columnCommentLike) {
		this.columnCommentLike = columnCommentLike;
	}
	
	
}
