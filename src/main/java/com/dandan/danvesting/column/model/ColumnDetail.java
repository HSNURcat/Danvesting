package com.dandan.danvesting.column.model;

import java.util.List;

import com.dandan.danvesting.column.comment.model.ColumnCommentDetail;

public class ColumnDetail {
	private Column column;
	private List<ColumnCommentDetail> columnCommentDetailList;
	
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public List<ColumnCommentDetail> getColumnCommentDetailList() {
		return columnCommentDetailList;
	}
	public void setColumnCommentDetailList(List<ColumnCommentDetail> columnCommentDetailList) {
		this.columnCommentDetailList = columnCommentDetailList;
	}
	
	
	
}
