package com.dandan.danvesting.stock.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class GetAPIDataTest {
	
	public static void main(String[] args) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		
		LocalDate now = LocalDate.now();
		System.out.println(now.toString());
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "QQQ");
		params.put("apiKey", "zd3fmYrcDdg3TJsrWXPf2oEkch0tefRH");
		
		ResponseEntity<String> responseEntity = 
		restTemplate.getForEntity(
				"https://api.polygon.io/v2/aggs/ticker/" + params.get("name") + "/range/1/day/2020-06-01/2020-06-17?apiKey=" + params.get("apiKey")
				, String.class);
		
		System.out.println(responseEntity);
		System.out.println(responseEntity);
		
		System.out.println(responseEntity.getBody());
		System.out.println(responseEntity.getStatusCodeValue());
		
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		
		String stringline = 
				restTemplate.getForObject(
						"https://api.polygon.io/v2/aggs/ticker/" + params.get("name") + "/range/1/day/2020-06-01/2020-06-17?apiKey=" + params.get("apiKey")
						, String.class);
		
		System.out.println(stringline);
		
		//json전체 출력
		JsonParser parser = new JsonParser(); 
		JsonObject obj = (JsonObject) parser.parse(stringline);
		System.out.println("json전체 출력 : " + obj);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//ticker만 출력
		JsonPrimitive ticker = (JsonPrimitive)obj.get("ticker");
		System.out.println(ticker);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//queryCount만 출력
		JsonPrimitive queryCount = (JsonPrimitive)obj.get("queryCount");		
		System.out.println(queryCount.getAsInt());//int형으로 변환
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//adjusted만 출력
		JsonPrimitive adjusted = (JsonPrimitive)obj.get("adjusted");		
		System.out.println(adjusted.getAsBoolean());//int형으로 변환
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 출력
		JsonArray results = (JsonArray) obj.get("results");
		System.out.println("result부분 출력 : " + results);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 1번째 json만 출력
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap();
		JsonObject first = (JsonObject) results.get(0);
		map = (Map) gson.fromJson(first.getAsJsonObject(), map.getClass());
		System.out.println(map);
		System.out.println(map.get("v"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 1번째 json "v"만 출력
		JsonPrimitive firstElementV = (JsonPrimitive) first.get("v");
		System.out.println(firstElementV);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 1번째 json "t"만 출력
		JsonPrimitive firstElementT = (JsonPrimitive) first.get("t");
		Date date = new Date(firstElementT.getAsLong());
		System.out.println(date);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 전체 "V"만 출력
		for (int i = 0; i < results.size(); i++) {
			JsonObject result = (JsonObject) results.get(i);
			JsonPrimitive resultElementV = (JsonPrimitive) result.get("v");
			System.out.println(resultElementV);
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
	}

}
