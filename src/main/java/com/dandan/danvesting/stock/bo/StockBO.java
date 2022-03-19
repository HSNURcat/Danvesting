package com.dandan.danvesting.stock.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dandan.danvesting.stock.comment.bo.StockCommentBO;
import com.dandan.danvesting.stock.comment.model.StockComment;
import com.dandan.danvesting.stock.comment.model.StockCommentDetail;
import com.dandan.danvesting.stock.model.CompanyInfo;
import com.dandan.danvesting.stock.model.StockBar;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

@Service
public class StockBO {
	
	@Autowired
	private StockCommentBO stockCommentBO;	
	
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
	
	
	public CompanyInfo getCompanyInfoJSON (int userId, String ticker) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		LocalDate now = LocalDate.now();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", ticker);
		params.put("apiKey", "zd3fmYrcDdg3TJsrWXPf2oEkch0tefRH");
		params.put("date", now.toString());
		
		String stringline = 
			restTemplate.getForObject(
					"https://api.polygon.io/v3/reference/tickers/" + params.get("name") +"?date=" + params.get("date") + "&apiKey=" + params.get("apiKey")
					, String.class);
		
		//DTO인스턴스화
		CompanyInfo companyInfo = new CompanyInfo();
		
		//json전체
		JsonParser parser = new JsonParser(); 
		JsonObject obj = (JsonObject) parser.parse(stringline);
				
		//result부분
		JsonObject results = (JsonObject) obj.get("results");
		
		//종목이름
		companyInfo.setTicker(ticker);
		
		//종목이름
		JsonPrimitive name = (JsonPrimitive) results.get("name");
		companyInfo.setName(name.toString().replace("\"", ""));
		
		//증권거래소 상 종목 id
		JsonPrimitive cik = (JsonPrimitive) results.get("cik");
		companyInfo.setCik(cik.toString().replace("\"", ""));
		
		//거래가능 여부
		JsonPrimitive active = (JsonPrimitive) results.get("active");
		companyInfo.setActive(active.getAsBoolean());
		
		//상장날짜
		JsonPrimitive listDate = (JsonPrimitive) results.get("list_date");
		companyInfo.setListDate(listDate.toString());

		//종목설명
		JsonPrimitive description = (JsonPrimitive) results.get("description");
		if (description != null) {
			companyInfo.setDescription(description.toString().replace("\"", ""));
		}
		
		//전화번호
		JsonPrimitive phoneNumber = (JsonPrimitive) results.get("phone_number");
		if (phoneNumber != null) {
			companyInfo.setPhoneNumber(phoneNumber.toString().replace("\"", ""));
		}
		
		//종목 본사 주소정보들
		JsonObject address = (JsonObject) results.get("address");
		
		if (address != null) {
			//종목 본사 주소(state)
			JsonPrimitive state = (JsonPrimitive) address.get("state");
			companyInfo.setState(state.toString().replace("\"", ""));
			
			//종목 본사 주소(city)
			JsonPrimitive city = (JsonPrimitive) address.get("city");
			companyInfo.setCity(city.toString().replace("\"", ""));
			
			//종목 본사 주소(세부주소)
			JsonPrimitive address1 = (JsonPrimitive) address.get("address1");
			companyInfo.setAddress1(address1.toString().replace("\"", ""));
			
			//종목 본사 주소(우편번호)
			JsonPrimitive postalCode = (JsonPrimitive) address.get("postal_code");
			companyInfo.setPostalCode(postalCode.toString().replace("\"", ""));
		}
		
		//종목 브랜드 정보들
		JsonObject branding = (JsonObject) results.get("branding");
		if (branding != null) {
			//브랜드로고 url
			JsonPrimitive logoUrl = (JsonPrimitive) branding.get("logo_url");
			if (logoUrl != null) {
				companyInfo.setLogoUrl(logoUrl.toString());
				
				//브랜드로고 url with APIKey
				String brandLogoUrl = logoUrl.toString().replace("\"", "");//쌍따옴표 제거
				companyInfo.setBrandLogoUrl(brandLogoUrl + "?apiKey=" + params.get("apiKey"));
				
			}
			
			//브랜드아이콘 url
			JsonPrimitive iconUrl = (JsonPrimitive) branding.get("icon_url");
			if (iconUrl != null) {
				companyInfo.setIconUrl(iconUrl.toString());
				
				//브랜드로고 url with APIKey
				String brandIconUrl = iconUrl.toString().replace("\"", "");//쌍따옴표 제거
				companyInfo.setBrandIconUrl(brandIconUrl + "?apiKey=" + params.get("apiKey"));
			}
		}
		
		//종목 홈페이지
		JsonPrimitive homepage = (JsonPrimitive) results.get("homepage_url");
		if(homepage != null) {
			String homepageUrl = homepage.toString().replace("\"", "");//쌍따옴표 제거
			if (homepageUrl != null) {
				companyInfo.setHomepageUrl(homepageUrl);
			}
		}
		
		//종목 댓글들
		List<StockCommentDetail> stockComments = stockCommentBO.getStockComments(userId, ticker);
		companyInfo.setStockCommentDetails(stockComments);
		
		return companyInfo;
	}
	
	
}
