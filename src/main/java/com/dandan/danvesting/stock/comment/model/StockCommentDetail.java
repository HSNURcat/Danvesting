package com.dandan.danvesting.stock.comment.model;

import com.dandan.danvesting.stock.comment.like.model.StockCommentLike;

public class StockCommentDetail {
	private StockComment stockComment;//stockComment테이블 컬럼데이터
	private StockCommentLike stockCommentLike;//stockCommentLike테이블 컬럼 데이터
	
	public StockComment getStockComment() {
		return stockComment;
	}
	public void setStockComment(StockComment stockComment) {
		this.stockComment = stockComment;
	}
	public StockCommentLike getStockCommentLike() {
		return stockCommentLike;
	}
	public void setStockCommentLike(StockCommentLike stockCommentLike) {
		this.stockCommentLike = stockCommentLike;
	}
}
