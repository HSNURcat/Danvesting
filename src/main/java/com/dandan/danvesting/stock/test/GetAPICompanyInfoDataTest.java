package com.dandan.danvesting.stock.test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class GetAPICompanyInfoDataTest {
	public static void main(String[] args) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		LocalDate now = LocalDate.now();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "AAPL");
		params.put("apiKey", "zd3fmYrcDdg3TJsrWXPf2oEkch0tefRH");
		params.put("date", now.toString());
		
		String stringline = 
			restTemplate.getForObject(
					"https://api.polygon.io/v3/reference/tickers/" + params.get("name") +"?date=" + params.get("date") + "&apiKey=" + params.get("apiKey")
					, String.class);
		
		System.out.println(stringline);
		
		//json전체 출력
		JsonParser parser = new JsonParser(); 
		JsonObject obj = (JsonObject) parser.parse(stringline);
		System.out.println("json전체 출력 : " + obj);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//result부분 출력
		JsonObject results = (JsonObject) obj.get("results");
		System.out.println("result부분 출력 : " + results);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목이름출력
		JsonPrimitive name = (JsonPrimitive) results.get("name");
		System.out.println("종목이름 출력 : " + name);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 유형
		JsonPrimitive type = (JsonPrimitive) results.get("type");
		System.out.println("종목 유형 : " + type);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//증권거래소 상 종목 id
		JsonPrimitive cik = (JsonPrimitive) results.get("cik");
		System.out.println("증권거래소 상 종목 id : " + cik);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//상장폐지 여부
		JsonPrimitive active = (JsonPrimitive) results.get("active");
		System.out.println("상장폐지 여부 : " + active);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 국가
		JsonPrimitive country = (JsonPrimitive) results.get("locale");
		System.out.println("종목국가 출력 : " + country);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//상장날짜
		JsonPrimitive listDate = (JsonPrimitive) results.get("list_date");
		System.out.println("상장날짜 출력 : " + listDate);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목설명
		JsonPrimitive description = (JsonPrimitive) results.get("description");
		System.out.println("종목 설명 : " + description);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//전화번호
		JsonPrimitive phoneNumber = (JsonPrimitive) results.get("phone_number");
		System.out.println("본사 전화번호 : " + phoneNumber);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 본사 주소정보들
		JsonObject address = (JsonObject) results.get("address");
		System.out.println("종목 본사 주소 정보들 : " + address);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 본사 주소(state)
		JsonPrimitive state = (JsonPrimitive) address.get("state");
		System.out.println("종목 본사 주소(state) : " + state);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 본사 주소(city)
		JsonPrimitive city = (JsonPrimitive) address.get("city");
		System.out.println("종목 본사 주소(city) : " + city);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 본사 주소(세부주소)
		JsonPrimitive address1 = (JsonPrimitive) address.get("address1");
		System.out.println("종목 본사 주소(세부주소) : " + address1);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 본사 주소(우편번호)
		JsonPrimitive postalCode = (JsonPrimitive) address.get("postal_code");
		System.out.println("종목 본사 주소(우편번호) : " + postalCode);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 브랜드 정보들
		JsonObject branding = (JsonObject) results.get("branding");
		System.out.println("종목 브랜드 정보들 : " + branding);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//브랜드로고 url
		JsonPrimitive logoUrl = (JsonPrimitive) branding.get("logo_url");
		System.out.println("브랜드 로고 url : " + logoUrl);
		String brandLogoUrl = logoUrl.toString().replace("\"", "");//쌍따옴표 제거
		System.out.println("apiKey 첨부 브랜드 로고 url : " + brandLogoUrl + "?apiKey=" + params.get("apiKey"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
			
		//브랜드아이콘 url
		JsonPrimitive iconUrl = (JsonPrimitive) branding.get("icon_url");
		System.out.println("브랜드 아이콘 url : " + iconUrl);
		String brandIconUrl = iconUrl.toString().replace("\"", "");//쌍따옴표 제거
		System.out.println("apiKey 첨부 브랜드 로고 url : " + brandIconUrl + "?apiKey=" + params.get("apiKey"));
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
		
		//종목 홈페이지
		JsonPrimitive homepage = (JsonPrimitive) results.get("homepage_url");
		System.out.println("종목 홈페이지 URL : " + homepage);
		String homepageUrl = homepage.toString().replace("\"", "");//쌍따옴표 제거
		System.out.println(homepageUrl);
		System.out.println("----------------------------------------------------------------------------------------------------------------------");
				
	}
}
