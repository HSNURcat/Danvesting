package com.dandan.danvesting.column.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ColumnListCrawlingTest {
	public static void main(String[] args) {
		//칼럼 url들 크롤링
		String URL = "https://kr.investing.com/analysis/most-popular-analysis";
			
		try {
			Document doc = Jsoup.connect(URL).get();
			
			//class="articleItem"인 요소들 끌어와서 저장
			Elements columns = doc.getElementsByClass("articleItem");
			
			for (Element element : columns) {
				//data-id 속성 끌어와서 저장
				String columnDataId = element.attr("data-id");
				
				//data-id가 비어있으면 작업X, 안 비어있으면, 칼럼URL생성
				if(columnDataId =="") {
					
				} else if(columnDataId.startsWith("20")){
					String columnURL = "https://kr.investing.com/analysis/article-" + columnDataId;
					
					System.out.println(columnURL);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
