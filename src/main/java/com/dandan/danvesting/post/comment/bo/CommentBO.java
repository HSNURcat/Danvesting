package com.dandan.danvesting.post.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.post.comment.dao.CommentDAO;
import com.dandan.danvesting.post.comment.like.bo.CommentLikeBO;
import com.dandan.danvesting.post.comment.like.model.CommentLike;
import com.dandan.danvesting.post.comment.model.Comment;
import com.dandan.danvesting.post.comment.model.CommentDetail;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private CommentLikeBO commentLikeBO;
	
	public int addComment(int userId, int postId, String nickName, String commentContent) {
		return commentDAO.insertComment(userId, postId, nickName, commentContent);
	}
	
	public List<CommentDetail> getComments(int userId, int postId) {
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		// 댓글 작성자, 내용 등 comment테이블 데이터 가져옴
		List<Comment> comments = commentDAO.selectComments(postId);
		
		//like테이블 데이터 중 댓글 좋아요/싫어요 데이터 가져옴
		List<CommentLike>commentLikeList = new ArrayList<CommentLike>();
		for (Comment comment : comments) {
			CommentLike commentLike = commentLikeBO.getCommentLikeDislikeCount(userId, postId, comment.getId());
			commentLikeList.add(commentLike);
		}
		
		for (int i = 0; i < comments.size(); i++) { //comment테이블 데이터 + 댓글 좋아요 싫어요 데이터
			if(comments.get(i).getId() == commentLikeList.get(i).getCommentId()) {
				CommentDetail commentDetail = new CommentDetail();
				commentDetail.setComment(comments.get(i));
				commentDetail.setCommentLikeDislike(commentLikeList.get(i));
				
				commentDetailList.add(commentDetail);
			}
		}
		
		return commentDetailList;
	}
	
}
