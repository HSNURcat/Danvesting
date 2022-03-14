package com.dandan.danvesting.stock.model;

import java.util.List;
import java.util.Map;

public class StockBar {
	private String ticker;
	private String status;
	private int queryCount;
	private int resultsCount;
	private boolean adjusted;
	private List<Map<String, Object>> results;
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}
	public int getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(int resultsCount) {
		this.resultsCount = resultsCount;
	}
	public boolean isAdjusted() {
		return adjusted;
	}
	public void setAdjusted(boolean adjusted) {
		this.adjusted = adjusted;
	}
	public List<Map<String, Object>> getResults() {
		return results;
	}
	public void setResults(List<Map<String, Object>> results) {
		this.results = results;
	}

	
}
