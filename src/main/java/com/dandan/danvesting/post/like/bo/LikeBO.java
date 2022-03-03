package com.dandan.danvesting.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.bo.PostBO;
import com.dandan.danvesting.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	//좋아요 추가
	public int addPostLike(int postId, int userTablePK, String nickName) {
		return likeDAO.insertPostLike(postId, userTablePK, nickName);
	}
	
	//좋아요 삭제
	public int deletePostLike(int postId, int userTablePK) {
		return likeDAO.deletePostLikeByUserId(postId, userTablePK);
	}
	
	//싫어요 추가
	public int addPostDisike(int postId, int userTablePK, String nickName) {
		return likeDAO.insertPostDislike(postId, userTablePK, nickName);
	}
	
	//싫어요 삭제
	public int deletePostDislike(int postId, int userTablePK) {
		return likeDAO.deletePostDislikeByUserId(postId, userTablePK);
	}
	
	//postId로 게시물 좋아요 개수 조회
	public int getPostLikeCount(int postId) {
		return likeDAO.selectPostLikeCount(postId);
	}
	
	//postId로 게시물 싫어요 개수 조회
	public int getPostDislikeCount(int postId) {
		return likeDAO.selectPostDislikeCount(postId);
	}
	
	//postId와 userId로 좋아요 여부 확인
	public boolean isPostLike(int postId, int userId) {
		int count = likeDAO.selectPostLikeCountByUserId(postId, userId);
		
		if(count != 0) {//이미 좋아요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//postId와 userId로 싫어요 여부 확인
	public boolean isPostDislike(int postId, int userId) {
		int count = likeDAO.selectPostDislikeCountByUserId(postId, userId);
		
		if(count != 0) {//이미 싫어요 상태일때 true반환
			return true;
		} else {
			return false;
		}
	}
	
	//postId와 userId로 좋아요 여부 확인 후 좋아요 추가/삭제
	public boolean PostLike(int postId, int userId, String nickName) {
		
		//게시물의 좋아요 여부 확인
		boolean isPostLike = this.isPostLike(postId, userId);
		
		if (isPostLike) {//이미 좋아요 상태일때 
			//좋아요 삭제 후, 삭제완료(true)반환
			this.deletePostLike(postId, userId);
			return true;
			
		} else {// 좋아요 상태 아닐때,
			//좋아요 추가 후, 삭제안함(false)반환
			this.addPostLike(postId, userId, nickName);
			return false;
		}
	}
	
	//postId와 userId로 싫어요 여부 확인 후 싫어요 추가/삭제
	public boolean PostDislike(int postId, int userId, String nickName) {
		
		//게시물의 싫어요 여부 확인
		boolean isPostDislike = this.isPostDislike(postId, userId);
		
		if (isPostDislike) {//이미 싫어요 상태일때 
			//싫어요 삭제 후, 삭제완료(true)반환
			this.deletePostDislike(postId, userId);
			return true;
			
		} else {// 싫어요 상태 아닐때,
			//싫어요 추가 후, 삭제안함(false)반환
			this.addPostDisike(postId, userId, nickName);
			return false;
		}
	}
	
	
}
