package com.dandan.danvesting.column.comment.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.column.comment.like.dao.ColumnCommentLikeDAO;
import com.dandan.danvesting.column.comment.like.model.ColumnCommentLike;
import com.dandan.danvesting.stock.comment.like.model.StockCommentLike;

@Service
public class ColumnCommentLikeBO {
	@Autowired
	private ColumnCommentLikeDAO columnCommentLikeDAO;
	
	//댓글 좋아요 추가
	public int addCommentLike(int userId, int columnId, String nickName, int commentId) {
		return columnCommentLikeDAO.insertCommentLike(userId, columnId, nickName, commentId);
	}
	
	//해당 사용자의 댓글 좋아요 취소
	public int deleteCommentLike(int userId, int columnId, int commentId) {
		return columnCommentLikeDAO.deleteCommentLikeByUserId(userId, columnId, commentId);
	}
	
	
	//columnId, userId, commentId로 해당 사용자의 댓글 좋아요 여부 확인
	public boolean isCommentLike(int userId, int columnId, int commentId) {
		int count = columnCommentLikeDAO.selectIsCommentLikeByUserId(userId, columnId, commentId);
		
		if (count != 0) {//이미 좋아요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//columnId, userId, commentId로 좋아요 여부 확인 후 좋아요 추가/삭제
	public boolean columnCommentLike(String nickName, int userId, int columnId, int commentId) {
				
		//댓글의 좋아요 여부 확인
		boolean isCommentLike = this.isCommentLike(userId, columnId, commentId);
				
		if (isCommentLike) {//이미 좋아요 상태일때
			//좋아요 삭제 후, 삭제완료(true)반환
			this.deleteCommentLike(userId, columnId, commentId);
			return true;
					
		} else {// 좋아요 상태 아닐때,
			//좋아요 추가 후, 삭제안함(false)반환
			this.addCommentLike(userId, columnId, nickName, commentId);
			return false;
		}
	}
	
	// 댓글 싫어요 추가
	public int addCommentDislike(String nickName, int userId, int columnId, int commentId) {
		return columnCommentLikeDAO.insertCommentDislike(userId, columnId, nickName, commentId);
	}
		
	// 해당 사용자의 댓글 싫어요 취소
	public int deleteCommentDislike(int userId, int columnId, int commentId) {
		return columnCommentLikeDAO.deleteCommentDislikeByUserId(userId, columnId, commentId);
	}
		
	//columnId, userId, commentId로 해당 사용자의 댓글 싫어요 여부 확인
	public boolean isCommentDislike(int userId, int columnId, int commentId) {
		int count = columnCommentLikeDAO.selectIsCommentDislikeByUserId(userId, columnId, commentId);
		
		if(count != 0) {//이미 싫어요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
		
	//columnId, userId, commentId로 싫어요 여부 확인 후 싫어요 추가/삭제
	public boolean columnCommentDislike(String nickName, int userId, int columnId, int commentId) {
			
		//댓글의 싫어요 여부 확인
		boolean isCommentDislike = this.isCommentDislike(userId, columnId, commentId);
		
		if (isCommentDislike) {//이미 싫어요 상태일때
			//싫어요 삭제 후, 삭제완료(true)반환
			this.deleteCommentDislike(userId, columnId, commentId);
			return true;
				
		} else {// 싫어요 상태 아닐때,
			//싫어요 추가 후, 삭제안함(false)반환
			this.addCommentDislike(nickName, userId, columnId, commentId);
			return false;
		}
	}
	
	public ColumnCommentLike getcolumnCommentLikeDislikeCount(int userId, int columnId, int commentId) {
		//댓글 좋아요/싫어요 count조회 후 데이터 저장
		ColumnCommentLike columnCommentLike = new ColumnCommentLike();
		columnCommentLike.setColumnId(columnId);
		columnCommentLike.setCommentId(commentId);
		columnCommentLike.setLikeCount(columnCommentLikeDAO.selectCommentLikeCount(columnId, commentId));
		columnCommentLike.setDislikeCount(columnCommentLikeDAO.selectCommentDislikeCount(columnId, commentId));
		
		//좋아요 상태 데이터 저장
		if(this.isCommentLike(userId, columnId, commentId)) {//좋아요 상태일때
			columnCommentLike.setCommentLike(true);//true넣음
		} else {
			columnCommentLike.setCommentLike(false);
		}
		
		//싫어요 상태 데이터 저장
		if(this.isCommentDislike(userId, columnId, commentId)) {//싫어요 상태일때
			columnCommentLike.setCommentDislike(true);//true넣음
		} else {
			columnCommentLike.setCommentDislike(false);
		}
		return columnCommentLike;
	}
	
	
	
}
