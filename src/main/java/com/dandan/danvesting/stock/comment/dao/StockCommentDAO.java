package com.dandan.danvesting.stock.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommentDAO {
	public int insertStockComment(
			@Param("userId") int userId,
			@Param("cik") String cik, 
			@Param("ticker") String ticker, 
			@Param("nickName") String nickName, 
			@Param("content") String commentContent);
}
