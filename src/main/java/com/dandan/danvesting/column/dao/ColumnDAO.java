package com.dandan.danvesting.column.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import com.dandan.danvesting.column.model.Column;

@Repository
public interface ColumnDAO {
	public int insertColumn(
			@Param("url") String columnURL, 
			@Param("title") String title, 
			@Param("content") String content);
	
	public List<Column> selectColumns();
	
	public Column selectColumnByColumnId(@Param("columnId") int columnId);
	
}
