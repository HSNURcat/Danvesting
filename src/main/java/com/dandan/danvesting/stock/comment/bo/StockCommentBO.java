package com.dandan.danvesting.stock.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.stock.comment.dao.StockCommentDAO;

@Service
public class StockCommentBO {
	
	@Autowired
	private StockCommentDAO stockCommentDAO;
	
	public int addComment(int userId, String cik, String nickname, String ticker, String commentContent) {
		int count = stockCommentDAO.insertStockComment(userId, cik, ticker, nickname, commentContent);
		return count;
	}
	
	
}
