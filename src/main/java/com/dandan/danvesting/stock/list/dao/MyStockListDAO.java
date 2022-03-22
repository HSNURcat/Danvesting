package com.dandan.danvesting.stock.list.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.stock.list.model.StockList;

@Repository
public interface MyStockListDAO {
	
	public List<StockList> selectMyStockList(@Param("userId") int userId);
	
	public int insertMyStockList(@Param("userId") int userId, @Param("ticker") String ticker); 
}
