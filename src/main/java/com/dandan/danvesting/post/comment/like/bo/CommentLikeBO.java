package com.dandan.danvesting.post.comment.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.comment.like.dao.CommentLikeDAO;
import com.dandan.danvesting.post.comment.like.model.CommentLike;


@Service
public class CommentLikeBO {
	@Autowired
	private CommentLikeDAO commentLikeDAO;
	
	// 댓글 좋아요 추가
	public int addCommentLike(String nickName, int userId, int postId, int commentId) {
		return commentLikeDAO.insertCommentLike(userId, postId, nickName, commentId);
	}
	
	// 해당 사용자의 댓글 좋아요 취소
	public int deleteCommentLike(int userId, int postId, int commentId) {
		return commentLikeDAO.deleteCommentLikeByUserId(userId, postId, commentId);
	}
	
	//postId, userId, commentId로 해당 사용자의 댓글 좋아요 여부 확인
	public boolean isCommentLike(int userId, int postId, int commentId) {
		int count = commentLikeDAO.selectIsCommentLikeByUserId(userId, postId, commentId);
		
		if(count != 0) {//이미 좋아요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//postId, userId, commentId로 좋아요 여부 확인 후 좋아요 추가/삭제
	public boolean commentLike(String nickName, int userId, int postId, int commentId) {
		
		//댓글의 좋아요 여부 확인
		boolean isCommentLike = this.isCommentLike(userId, postId, commentId);
		
		if (isCommentLike) {//이미 좋아요 상태일때
			//좋아요 삭제 후, 삭제완료(true)반환
			this.deleteCommentLike(userId, postId, commentId);
			return true;
			
		} else {// 좋아요 상태 아닐때,
			//좋아요 추가 후, 삭제안함(false)반환
			this.addCommentLike(nickName, userId, postId, commentId);
			return false;
		}
		
	}
	
	// 댓글 싫어요 추가
	public int addCommentDislike(String nickName, int userId, int postId, int commentId) {
		return commentLikeDAO.insertCommentDislike(userId, postId, nickName, commentId);
	}
	
	// 해당 사용자의 댓글 싫어요 취소
	public int deleteCommentDislike(int userId, int postId, int commentId) {
		return commentLikeDAO.deleteCommentDislikeByUserId(userId, postId, commentId);
	}
	
	//postId, userId, commentId로 해당 사용자의 댓글 싫어요 여부 확인
	public boolean isCommentDislike(int userId, int postId, int commentId) {
		int count = commentLikeDAO.selectIsCommentDislikeByUserId(userId, postId, commentId);
		
		if(count != 0) {//이미 싫어요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//postId, userId, commentId로 싫어요 여부 확인 후 싫어요 추가/삭제
	public boolean commentDislike(String nickName, int userId, int postId, int commentId) {
		
		//댓글의 싫어요 여부 확인
		boolean isCommentDislike = this.isCommentDislike(userId, postId, commentId);
		
		if (isCommentDislike) {//이미 싫어요 상태일때
			//싫어요 삭제 후, 삭제완료(true)반환
			this.deleteCommentDislike(userId, postId, commentId);
			return true;
			
		} else {// 싫어요 상태 아닐때,
			//싫어요 추가 후, 삭제안함(false)반환
			this.addCommentDislike(nickName, userId, postId, commentId);
			return false;
		}
	}
	
	public CommentLike getCommentLikeDislikeCount(int userId, int postId, int commentId) {
		// 댓글 좋아요/싫어요 조회후 데이터 저장
		CommentLike commentLike = new CommentLike();
		commentLike.setPostId(postId);
		commentLike.setCommentId(commentId);
		commentLike.setLikeCount(commentLikeDAO.selectCommentLikeCount(postId, commentId));
		commentLike.setDislikeCount(commentLikeDAO.selectCommentDislikeCount(postId, commentId));
		
		//좋아요 상태 데이터 저장
		if(this.isCommentLike(userId, postId, commentId)) {//좋아요 상태일때
			commentLike.setCommentLike(true);//true넣음
		} else {
			commentLike.setCommentLike(false);
		}
		
		//싫어요 상태 데이터 저장
		if(this.isCommentDislike(userId, postId, commentId)) {//싫어요 상태일때
			commentLike.setCommentDislike(true);//true넣음
		} else {
			commentLike.setCommentDislike(false);
		}
		
		return commentLike;
	}
	
	//게시물 삭제시, 해당 게시물 댓글 좋아요 삭제
	public int deleteCommentsLike(int postId) {
		int count = commentLikeDAO.deleteCommentsLike(postId);
		return count;
	}
	
}
