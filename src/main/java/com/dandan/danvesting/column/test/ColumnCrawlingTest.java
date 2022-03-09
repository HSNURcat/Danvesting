package com.dandan.danvesting.column.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ColumnCrawlingTest {
	public static void main(String[] args) {
		
		String URL = "https://kr.investing.com/analysis/article-200439157";
		
		try {
			Document doc = Jsoup.connect(URL).get();
			
			Elements title = doc.getElementsByClass("articleHeader");
			System.out.println(title.text());
			
			String titleString = "";
			Elements content = doc.getElementsByClass("WYSIWYG");
			String contentString = "";
			
			titleString = titleString.concat(title.toString());
			contentString = contentString.concat(content.toString());
			
//			System.out.println(titleString);
//			System.out.println(contentString);
			
//			for (Element element : content) {
//				System.out.println(element);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
