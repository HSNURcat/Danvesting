package com.dandan.danvesting.stock.list.bo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.stock.list.dao.MyStockListDAO;
import com.dandan.danvesting.stock.list.model.StockList;

@Service
public class MyStockListBO {
	
	@Autowired
	private MyStockListDAO myStockListDAO;
	
	//오늘날짜 도출
	LocalDate now = LocalDate.now();
	String today = now.toString();
	
	//해당 사용자의 종목 가져옴
	public List<StockList> getMyStock(int userId) {
		return myStockListDAO.selectMyStockList(userId);
	}
	
	//해당 사용자의 특정 종목 저장
	public int addMyStock(int userId, String ticker) {
		return myStockListDAO.insertMyStockList(userId, ticker);
	}
	
	//해당 사용자의 특정 종목 삭제
	public int deleteMyStock(int userId, int stockListId) {
		return myStockListDAO.deleteMyStockList(userId, stockListId);
	}
		
}
