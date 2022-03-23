package com.dandan.danvesting.column.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.column.comment.dao.ColumnCommentDAO;
import com.dandan.danvesting.column.comment.like.bo.ColumnCommentLikeBO;
import com.dandan.danvesting.column.comment.like.model.ColumnCommentLike;
import com.dandan.danvesting.column.comment.model.ColumnComment;
import com.dandan.danvesting.column.comment.model.ColumnCommentDetail;

@Service
public class ColumnCommentBO {
	
	@Autowired
	private ColumnCommentDAO columnCommentDAO;
	
	@Autowired
	private ColumnCommentLikeBO columnCommentLikeBO;
	
	public int addComment(int userId, String nickName, int columnId, String content) {
		return columnCommentDAO.insertComment(userId, nickName, columnId, content);
	}
	
	public List<ColumnCommentDetail> getColumnComments(int userId, int columnId) {
		List<ColumnCommentDetail>columnCommentDetailList = new ArrayList<>();
		
		//댓글 작성자, 내용 등 columnComment테이블 데이터 가져옴
		List<ColumnComment>columnComments = columnCommentDAO.selectComments(columnId);
		
		//columnCommentLike테이블 데이터 가져옴
		List<ColumnCommentLike> columnCommentLikes = new ArrayList<>();
		for (ColumnComment columnComment : columnComments) {
			ColumnCommentLike columnCommentLike = columnCommentLikeBO.getcolumnCommentLikeDislikeCount(userId, columnId, columnComment.getId());
			columnCommentLikes.add(columnCommentLike);
		}
		
		for (int i = 0; i < columnComments.size(); i++) { //columnComment테이블 데이터 + columnCommentLike테이블 데이터
			if(columnComments.get(i).getId() == columnCommentLikes.get(i).getCommentId()) {
				ColumnCommentDetail columnCommentDetail = new ColumnCommentDetail();
				columnCommentDetail.setColumnComment(columnComments.get(i));
				columnCommentDetail.setColumnCommentLike(columnCommentLikes.get(i));
				
				columnCommentDetailList.add(columnCommentDetail);
			}
		}
		return columnCommentDetailList;
	}
}
