package com.dandan.danvesting.stock.list;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dandan.danvesting.stock.list.bo.MyStockListBO;

@Controller
@RequestMapping("/post")
public class MyStockListController {
	
	@Autowired
	private MyStockListBO myStockListBO;
	
	@GetMapping("/stock/mylist")
	public String getmyStockList(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		model.addAttribute("stockList", myStockListBO.getMyStock(userId));
		
		return "stock/myStockList";
	}
	
	@ResponseBody
	@GetMapping("/stock/addStock")
	public Map<String, String> addMyStock(@RequestParam("ticker") String ticker, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		
		int count = myStockListBO.addMyStock(userId, ticker);
		
		Map<String, String> result = new HashMap<String, String>();
		if (count != 0) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		return result;
	}
}
