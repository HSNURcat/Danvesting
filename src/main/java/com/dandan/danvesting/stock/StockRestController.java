package com.dandan.danvesting.stock;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dandan.danvesting.stock.bo.StockBO;
import com.dandan.danvesting.stock.model.StockBar;

@RestController
@RequestMapping("/post/stock")
public class StockRestController {
	
	@Autowired
	private StockBO stockBO;
	
	@GetMapping("/detail_price")
	public Map<String, StockBar> getStockDetailJSON(@RequestParam("ticker") String ticker,
			@RequestParam("search_from_date") String fromDate) {
		StockBar stockBarData = stockBO.getStockDetailJSON(ticker, fromDate);
		
		Map<String, StockBar> result = new HashMap<>();
		result.put("stockBar", stockBarData);
		
		return result;
	}
	
}
