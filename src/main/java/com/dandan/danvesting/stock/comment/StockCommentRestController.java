package com.dandan.danvesting.stock.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.stock.comment.bo.StockCommentBO;

@RestController
@RequestMapping("/post/stock")
public class StockCommentRestController {
	
	@Autowired
	private StockCommentBO stockCommentBO;
	
	@PostMapping("/comment/create")
	public Map<String, String> addStockComment(
			@RequestParam("ticker") String ticker, 
			@RequestParam("cik") String cik, 
			@RequestParam("commentContent") String commentContent,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		String nickName = (String)session.getAttribute("nickName");
		
		int count = stockCommentBO.addComment(userId, cik, nickName, ticker, commentContent);
		
		Map<String, String> result = new HashMap<>();
		
		if (count != 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		return result;
	}
	
	
}
