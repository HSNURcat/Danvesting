package com.dandan.danvesting.stock.comment.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.comment.like.model.CommentLike;
import com.dandan.danvesting.stock.comment.like.dao.StockCommentLikeDAO;
import com.dandan.danvesting.stock.comment.like.model.StockCommentLike;

@Service
public class StockCommentLikeBO {
	
	@Autowired
	private StockCommentLikeDAO stockCommentLikeDAO;
	
	// 댓글 좋아요 추가
	public int addCommentLike(int userId, String ticker, String nickName, int commentId) {
		return stockCommentLikeDAO.insertCommentLike(userId, ticker, nickName, commentId);
	}
	
	// 해당 사용자의 댓글 좋아요 취소
	public int deleteCommentLike(int userId, String ticker, int commentId) {
		return stockCommentLikeDAO.deleteCommentLikeByUserId(userId, ticker, commentId);
	}
	
	//ticker, userId, commentId로 해당 사용자의 댓글 좋아요 여부 확인
	public boolean isCommentLike(int userId, String ticker, int commentId) {
		int count = stockCommentLikeDAO.selectIsCommentLikeByUserId(userId, ticker, commentId);
			
		if(count != 0) {//이미 좋아요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//ticker, userId, commentId로 좋아요 여부 확인 후 좋아요 추가/삭제
	public boolean stockCommentLike(String nickName, int userId, String ticker, int commentId) {
			
		//댓글의 좋아요 여부 확인
		boolean isCommentLike = this.isCommentLike(userId, ticker, commentId);
			
		if (isCommentLike) {//이미 좋아요 상태일때
			//좋아요 삭제 후, 삭제완료(true)반환
			this.deleteCommentLike(userId, ticker, commentId);
			return true;
				
		} else {// 좋아요 상태 아닐때,
			//좋아요 추가 후, 삭제안함(false)반환
			this.addCommentLike(userId, ticker, nickName, commentId);
			return false;
		}
	}
		
	
	// 댓글 싫어요 추가
	public int addCommentDislike(String nickName, int userId, String ticker, int commentId) {
		return stockCommentLikeDAO.insertCommentDislike(userId, ticker, nickName, commentId);
	}
	
	// 해당 사용자의 댓글 싫어요 취소
	public int deleteCommentDislike(int userId, String ticker, int commentId) {
		return stockCommentLikeDAO.deleteCommentDislikeByUserId(userId, ticker, commentId);
	}
	
	//postId, userId, commentId로 해당 사용자의 댓글 싫어요 여부 확인
	public boolean isCommentDislike(int userId, String ticker, int commentId) {
		int count = stockCommentLikeDAO.selectIsCommentDislikeByUserId(userId, ticker, commentId);
		
		if(count != 0) {//이미 싫어요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//postId, userId, commentId로 싫어요 여부 확인 후 싫어요 추가/삭제
	public boolean stockCommentDislike(String nickName, int userId, String ticker, int commentId) {
		
		//댓글의 싫어요 여부 확인
		boolean isCommentDislike = this.isCommentDislike(userId, ticker, commentId);
		
		if (isCommentDislike) {//이미 싫어요 상태일때
			//싫어요 삭제 후, 삭제완료(true)반환
			this.deleteCommentDislike(userId, ticker, commentId);
			return true;
			
		} else {// 싫어요 상태 아닐때,
			//싫어요 추가 후, 삭제안함(false)반환
			this.addCommentDislike(nickName, userId, ticker, commentId);
			return false;
		}
	}
	
	public StockCommentLike getStockCommentLikeDislikeCount(int userId, String ticker, int commentId) {
		// 댓글 좋아요/싫어요 조회후 데이터 저장
		StockCommentLike stockCommentLike = new StockCommentLike();
		stockCommentLike.setTicker(ticker);
		stockCommentLike.setCommentId(commentId);
		stockCommentLike.setLikeCount(stockCommentLikeDAO.selectCommentLikeCount(ticker, commentId));
		stockCommentLike.setDislikeCount(stockCommentLikeDAO.selectCommentDislikeCount(ticker, commentId));
		
		//좋아요 상태 데이터 저장
		if(this.isCommentLike(userId, ticker, commentId)) {//좋아요 상태일때
			stockCommentLike.setCommentLike(true);//true넣음
		} else {
			stockCommentLike.setCommentLike(false);
		}
		
		//싫어요 상태 데이터 저장
		if(this.isCommentDislike(userId, ticker, commentId)) {//싫어요 상태일때
			stockCommentLike.setCommentDislike(true);//true넣음
		} else {
			stockCommentLike.setCommentDislike(false);
		}
		
		return stockCommentLike;
	}
	
}
