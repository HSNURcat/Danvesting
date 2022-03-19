package com.dandan.danvesting.stock.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.comment.model.CommentDetail;
import com.dandan.danvesting.stock.comment.dao.StockCommentDAO;
import com.dandan.danvesting.stock.comment.like.bo.StockCommentLikeBO;
import com.dandan.danvesting.stock.comment.like.model.StockCommentLike;
import com.dandan.danvesting.stock.comment.model.StockComment;
import com.dandan.danvesting.stock.comment.model.StockCommentDetail;

@Service
public class StockCommentBO {
	
	@Autowired
	private StockCommentDAO stockCommentDAO;
	
	@Autowired
	private StockCommentLikeBO stockCommentLikeBO;
	
	public int addComment(int userId, String cik, String nickname, String ticker, String commentContent) {
		int count = stockCommentDAO.insertStockComment(userId, cik, ticker, nickname, commentContent);
		return count;
	}
	
	public List<StockCommentDetail> getStockComments(int userId, String ticker) {
		List<StockCommentDetail>stockCommentDetailList = new ArrayList<>();
		
		// 댓글 작성자, 내용 등 stockComment테이블 데이터 가져옴
		List<StockComment> stockComments = stockCommentDAO.selectStockComments(ticker);
		
		//stockCommentLike테이블 데이터 가져옴
		List<StockCommentLike> stockCommentLikeList = new ArrayList<>();
		for (StockComment stockComment : stockComments) {
			StockCommentLike stockCommentLike = stockCommentLikeBO.getStockCommentLikeDislikeCount(userId, ticker, stockComment.getId());
			stockCommentLikeList.add(stockCommentLike);
		}
		
		for (int i = 0; i < stockComments.size(); i++) { //stockComment테이블 데이터 + stockCommentLike테이블 데이터
			if(stockComments.get(i).getId() == stockCommentLikeList.get(i).getCommentId()) {
				StockCommentDetail stockCommentDetail = new StockCommentDetail();
				stockCommentDetail.setStockComment(stockComments.get(i));
				stockCommentDetail.setStockCommentLike(stockCommentLikeList.get(i));
				
				stockCommentDetailList.add(stockCommentDetail);
			}
		}
		
		return stockCommentDetailList;
	}
	
}
