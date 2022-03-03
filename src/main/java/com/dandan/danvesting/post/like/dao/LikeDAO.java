package com.dandan.danvesting.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	//게시물 좋아요 추가
	public int insertPostLike(@Param("postId") int postId,
			@Param("userTablePK") int userTablePK,
			@Param("nickName") String nickName);
	
	//게시물 싫어요 추가
	public int insertPostDislike(@Param("postId") int postId,
			@Param("userTablePK") int userTablePK,
			@Param("nickName") String nickName);
	
	//게시물 좋아요 조회
	public int selectPostLikeCount(@Param("postId") int postId);
	
	//게시물 싫어요 조회
	public int selectPostDislikeCount(@Param("postId") int postId);
	
	//해당 사용자의 게시물 좋아요 여부 확인
	public int selectPostLikeCountByUserId(
			@Param("postId") int postId, @Param("userId") int userId);
	
	//해당 사용자의 게시물 싫어요 여부 확인
	public int selectPostDislikeCountByUserId(
			@Param("postId") int postId, @Param("userId") int userId);
	
	//해당 사용자의 게시물 좋아요 삭제
	public int deletePostLikeByUserId(
			@Param("postId") int postId, @Param("userId") int userId);
	
	//해당 사용자의 게시물 싫어요 삭제
	public int deletePostDislikeByUserId(
			@Param("postId") int postId, @Param("userId") int userId);
	
	
}
