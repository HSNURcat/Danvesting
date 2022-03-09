package com.dandan.danvesting.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dandan.danvesting.column.bo.ColumnBO;

@Component
public class Crawling {
	@Autowired
	private ColumnBO columnBO;
	
	@Scheduled(cron = "0 05 01 * * *")
	public void run() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      columnBO.addColumns();
	      
	      System.out.println("https://kr.investing.com/analysis/most-popular-analysis 크롤링 " + strDate);
	}
	
}
