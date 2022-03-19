package com.dandan.danvesting.stock.comment.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.stock.comment.like.bo.StockCommentLikeBO;

@RestController
@RequestMapping("/post/stock")
public class StockCommentLikeRestController {
	
	@Autowired
	private StockCommentLikeBO stockCommentLikeBO;
	
	@GetMapping("/comment/like")
	public Map<String, String> plusMinusLike(@RequestParam("ticker")String ticker, 
			@RequestParam("commentId")int commentId, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("id");
		String nickName = (String) session.getAttribute("nickName");
		
		//이미 좋아요 상태일때 좋아요 삭제 후, 삭제완료(true)반환
		//좋아요 상태 아닐때 좋아요 추가 후, 삭제안함(false)반환
		boolean minusPlusLike = stockCommentLikeBO.stockCommentLike(nickName, userId, ticker, commentId);
		
		Map<String, String> result = new HashMap<String, String>();
		
		if (minusPlusLike) {
			result.put("like", "minus");
		} else {
			result.put("like", "plus");
		}
		return result;
	}
	
	@GetMapping("/comment/dislike")
	public Map<String, String> CommentDisike(@RequestParam("ticker") String ticker,
			@RequestParam("commentId") int commentId, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		String nickName = (String)session.getAttribute("nickName");
		
		//이미 싫어요 상태일때 싫어요 삭제 후, 삭제완료(true)반환
		//싫어요 상태 아닐때 싫어요 추가 후, 삭제안함(false)반환
		boolean minusPlusDislike = stockCommentLikeBO.stockCommentDislike(nickName, userId, ticker, commentId);
		
		Map<String, String> result = new HashMap<>();
		
		if(minusPlusDislike) {//싫어요 삭제
			result.put("dislike", "minus");
		} else {
			result.put("dislike", "plus");
		}
		return result;
	}
	
}
