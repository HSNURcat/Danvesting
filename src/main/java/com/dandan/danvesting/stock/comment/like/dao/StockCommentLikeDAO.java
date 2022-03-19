package com.dandan.danvesting.stock.comment.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommentLikeDAO {
	//댓글 좋아요 추가
		public int insertCommentLike(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("nickName") String nickName,
				@Param("commentId") int commentId);
		
		//댓글 좋아요 조회
		public int selectCommentLikeCount(
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
		//해당 사용자의 댓글 좋아요 삭제
		public int deleteCommentLikeByUserId(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
		//해당 사용자의 댓글 좋아요 여부 확인
		public int selectIsCommentLikeByUserId(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
		//댓글 싫어요 추가
		public int insertCommentDislike(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("nickName") String nickName,
				@Param("commentId") int commentId);
		
		//댓글 싫어요 조회
		public int selectCommentDislikeCount(
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
		//해당 사용자의 댓글 싫어요 삭제
		public int deleteCommentDislikeByUserId(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
		//해당 사용자의 댓글 싫어요 여부 확인
		public int selectIsCommentDislikeByUserId(
				@Param("userId") int userId,
				@Param("ticker") String ticker,
				@Param("commentId") int commentId);
		
}
