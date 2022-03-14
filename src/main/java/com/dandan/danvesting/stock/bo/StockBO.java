package com.dandan.danvesting.stock.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dandan.danvesting.stock.model.StockBar;
import com.dandan.danvesting.stock.model.StockBarResult;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

@Service
public class StockBO {
	
	public StockBar getStockDetailJSON (String ticker, String fromDate) {
		//오늘날짜 도출
		LocalDate now = LocalDate.now();
		String today = now.toString();
		
		//API 이용 정보 저장 MAP
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", ticker);
		params.put("apiKey", "zd3fmYrcDdg3TJsrWXPf2oEkch0tefRH");
		params.put("today", today);
		params.put("fromDate", fromDate);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String stringline = restTemplate.getForObject(
						"https://api.polygon.io/v2/aggs/ticker/" + params.get("name") + "/range/1/day/" + params.get("fromDate") + "/" + params.get("today") + "?apiKey=" + params.get("apiKey")
						, String.class);
		
		//DTO인스턴스화
		StockBar stockBar = new StockBar();
		
		//json전체 출력
		JsonParser parser = new JsonParser(); 
		JsonObject obj = (JsonObject) parser.parse(stringline);
		
		//ticker만 떼서 DTO(stockBar)에 저장
		JsonPrimitive tickerInJSON = (JsonPrimitive)obj.get("ticker");
		stockBar.setTicker(tickerInJSON.toString());
		
		//status만 떼서 DTO(stockBar)에 저장
		JsonPrimitive statusInJSON = (JsonPrimitive)obj.get("status");
		stockBar.setStatus(statusInJSON.toString());
		
		//queryCount만 떼서 DTO(stockBar)에 저장
		JsonPrimitive queryCountInJSON = (JsonPrimitive)obj.get("queryCount");
		stockBar.setQueryCount(queryCountInJSON.getAsInt());
		
		//resultsCount만 떼서 DTO(stockBar)에 저장
		JsonPrimitive resultsCountInJSON = (JsonPrimitive)obj.get("resultsCount");
		stockBar.setResultsCount(resultsCountInJSON.getAsInt());
		
		//adjusted만 떼서 DTO(stockBar)에 저장
		JsonPrimitive adjustedInJSON = (JsonPrimitive)obj.get("adjusted");
		stockBar.setAdjusted(adjustedInJSON.getAsBoolean());
		
		//result부분 전체 가져옴
		JsonArray results = (JsonArray) obj.get("results");
		
		//results 하나하나 떼와서 DTO에 저장
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap();
		List<Map<String, Object>> mapList = new ArrayList<>();
		
		for (int i = 0; i < stockBar.getResultsCount(); i++) {
			JsonObject result = (JsonObject) results.get(i);
			map = (Map) gson.fromJson(result.getAsJsonObject(), map.getClass());
			mapList.add(map);
		}
		stockBar.setResults(mapList);
		return stockBar;
	}
	
	
	
	
	
}
