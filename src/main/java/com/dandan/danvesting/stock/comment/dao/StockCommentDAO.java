package com.dandan.danvesting.stock.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.stock.comment.model.StockComment;

@Repository
public interface StockCommentDAO {
	public int insertStockComment(
			@Param("userId") int userId,
			@Param("cik") String cik, 
			@Param("ticker") String ticker, 
			@Param("nickName") String nickName, 
			@Param("content") String commentContent);
	
	public List<StockComment> selectStockComments(@Param("ticker") String ticker);
}
