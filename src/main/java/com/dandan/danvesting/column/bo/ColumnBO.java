package com.dandan.danvesting.column.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dandan.danvesting.column.comment.bo.ColumnCommentBO;
import com.dandan.danvesting.column.comment.model.ColumnCommentDetail;
import com.dandan.danvesting.column.dao.ColumnDAO;
import com.dandan.danvesting.column.model.Column;
import com.dandan.danvesting.column.model.ColumnDetail;


@Service
public class ColumnBO {
	@Autowired
	private ColumnDAO columnDAO;
	
	@Autowired
	private ColumnCommentBO columnCommentBO;
	
	public Map<String, String> ExtractColumnData(String columnURL) {
		//매개변수로 받은 칼럼URL을 변수에 저장
		String URL = columnURL;
		
		String titleString = "";
		String contentString = "";
		
		//리턴할 값 저장할 map생성
		Map<String, String> columnData = new HashMap<>();
		
		try {
			//Jsoup.connect를 통해 해당url 페이지에서 데이터를 끌어와서 document doc에 저장한다
			Document doc = Jsoup.connect(URL).get();
			
			//doc변수에서 articlHeader클래스인 부분만 Elements title에 저장하고
			//doc변수에서 WYSIWYG클래스인 부분만 Elements content에 저장한다
			Elements title = doc.getElementsByClass("articleHeader");
			Elements content = doc.getElementsByClass("WYSIWYG");
			
			//Element 자료형 title변수 데이터를 String형으로 변환해서 titleString 변수에 저장
			titleString = title.text();
			
			//Element 자료형 content변수 데이터를 String형으로 변환해서 contentString 변수에 저장
			contentString = contentString.concat(content.toString());
			
			//앞에서 생성한 columnData 맵에 넣어준다
			columnData.put("title", titleString);
			columnData.put("content", contentString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columnData;
	}
	
	
	public void addColumns() {
		//칼럼 url들 크롤링
		String URL = "https://kr.investing.com/analysis/most-popular-analysis";
		
		List<String> columnURLList = new ArrayList<String>();
		
		try {
			Document doc = Jsoup.connect(URL).get();
			
			//class="articleItem"인 요소들 끌어와서 저장
			Elements columns = doc.getElementsByClass("articleItem");
			
			
			for (Element element : columns) {
				//data-id 속성 끌어와서 저장
				String columnDataId = element.attr("data-id");
				
				if(columnDataId =="") {//data-id가 비어있으면 작업X,
						
				} else if(columnDataId.startsWith("20")){
					String titleString = "";
					String contentString = "";
					
					// 안 비어있으면, 칼럼URL생성 후 변수에 저장
					String columnURL = "https://kr.investing.com/analysis/article-" + columnDataId;
					
					//칼럼 URL을 통해, 해당 칼럼의 제목, 내용을 가져옴
					Map<String, String> columnData = this.ExtractColumnData(columnURL);
					
					String title = columnData.get("title");
					String content  = columnData.get("content");
					
					columnDAO.insertColumn(columnURL, title, content);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//모든 칼럼 가져오기
	public List<Column> getColumns() {
		return columnDAO.selectColumns();
	}
	
	
	
	public ColumnDetail getColumnByColumnId (int userId, int columnId) {
		ColumnDetail columnDetail = new ColumnDetail();
		
		//칼럼테이블 PK기반으로 칼럼가져오기
		Column column = columnDAO.selectColumnByColumnId(columnId);
		
		//해당 칼럼의 댓글 데이터들 가져오기
		List<ColumnCommentDetail> columnCommentDetails = columnCommentBO.getColumnComments(userId, columnId);
		
		columnDetail.setColumn(column);
		columnDetail.setColumnCommentDetailList(columnCommentDetails);
		
		return columnDetail;
	}
	
	
}





