package com.dandan.danvesting.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dandan.danvesting.common.FileManageService;
import com.dandan.danvesting.post.comment.bo.CommentBO;
import com.dandan.danvesting.post.comment.like.bo.CommentLikeBO;
import com.dandan.danvesting.post.comment.like.model.CommentLike;
import com.dandan.danvesting.post.comment.model.Comment;
import com.dandan.danvesting.post.comment.model.CommentDetail;
import com.dandan.danvesting.post.dao.PostDAO;
import com.dandan.danvesting.post.like.bo.LikeBO;
import com.dandan.danvesting.post.model.Post;
import com.dandan.danvesting.post.model.PostDetail;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private CommentLikeBO commentLikeBO;
	
	public int createPost(int userId, String nickName, String postTitle, String postText, MultipartFile file) {
		String filePath = FileManageService.saveFile(userId, file);
		return postDAO.insertPost(userId, nickName, postTitle, postText, filePath);
	}
	
	public List<Post> getPost() {
		return postDAO.getPost();
	}
	
	public PostDetail getPostDetail(int postId, int userId) {
		Post post = postDAO.getPostDetail(postId);// 게시물 게시자/게시날짜/제목/콘텐츠
		int postLikeCount = likeBO.getPostLikeCount(postId);//게시물 좋아요
		int postDislikeCount = likeBO.getPostDislikeCount(postId);//게시물 싫어요
		boolean isPostLike = likeBO.isPostLike(postId, userId);//게시물 좋아요 여부
		boolean isPostDislike = likeBO.isPostDislike(postId, userId);//게시물 싫어요 여부
		List<CommentDetail> commentDetailList = commentBO.getComments(userId, postId);//게시물 댓글 comment테이블 컬럼들 + 댓글 좋아요/싫어요
		
		PostDetail postDetail = new PostDetail();
		postDetail.setPost(post);
		postDetail.setPostLikeCount(postLikeCount);
		postDetail.setPostDislikeCount(postDislikeCount);
		postDetail.setPostLike(isPostLike);
		postDetail.setPostDislike(isPostDislike);
		postDetail.setCommentDetailList(commentDetailList);
		
		return postDetail;
	}
	
	public boolean deletePost(int postId, int userId) {
		
		//post데이터 가져옴
		Post post = postDAO.getPostDetail(postId);
		
		//post의 writerId(userId)와 세션의 userId가 같지 않을때
		if(post.getWriterId() != userId) {
			return false;
		} else {
			
			//게시물 첨부 파일 삭제
			FileManageService.removeFile(post.getImagePath());
			
			//게시물 댓글들 & 댓글의 좋아요/싫어요 삭제
			commentLikeBO.deleteCommentsLike(postId);
			
			//게시물 삭제
			int postCount = postDAO.deletePost(postId, userId); 
			
			if(postCount == 0) {
				//게시물 삭제 없을시 false(삭제 실패)반환
				return false;
			} else {
				//게시물 삭제 있을때 true(삭제성공)반환
				return true;
			}
			
		}
	}
}
