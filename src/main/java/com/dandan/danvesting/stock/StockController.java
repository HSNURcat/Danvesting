package com.dandan.danvesting.stock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dandan.danvesting.common.FileManageService;
import com.dandan.danvesting.stock.bo.StockBO;
import com.dandan.danvesting.stock.model.CompanyInfo;
import com.dandan.danvesting.stock.model.StockBar;

@Controller
@RequestMapping("/post")
public class StockController {
	@Autowired
	private StockBO stockBO;
	
	//로그 클래스
	private static Logger logger = LoggerFactory.getLogger(FileManageService.class);
	
	@GetMapping("/stock/detail_view")
	public String getStockDetail(@RequestParam("ticker")String ticker, 
			Model model, HttpServletRequest request) {
		
		try {
			
			HttpSession session = request.getSession();
			int userId = (Integer)session.getAttribute("id");
				
			CompanyInfo companyInfo = new CompanyInfo();
				
			companyInfo = stockBO.getCompanyInfoJSON(userId, ticker);
				
			model.addAttribute("companyInfo", companyInfo);
			return "stock/stockDetail";
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			String errorMessage = e.getMessage();
			
			// errorMessage에 429(too many request)가 포함되면 실행
			if(errorMessage.contains("429")) {
				return "errorpages/tooManyReq";
			}
			
			return "errorpages/notFoundPage";
		}
			
	}
	
}
