package com.dandan.danvesting.column;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dandan.danvesting.column.bo.ColumnBO;
import com.dandan.danvesting.column.model.Column;
import com.dandan.danvesting.column.model.ColumnDetail;

@Controller
public class ColumnController {
	@Autowired
	private ColumnBO columnBO;
	
//	//크롤링 작동 테스트 코드
//	@GetMapping("/crawlingTest")
//	public String crawlingTest() {
//		
//		columnBO.addColumns();
//		
//		return "column/crawlingTest";
//	}

	@GetMapping("/post/column_list")
	public String columnList(Model model) {
		List<Column> columns = columnBO.getColumns();
		
		model.addAttribute("columns", columns);
		return "column/columnList";
	}
	
	
	@GetMapping("/post/column_detail_view")
	public String columnDetail(@RequestParam("columnId") int columnId, 
			Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("id");
		ColumnDetail columndetail = columnBO.getColumnByColumnId(userId, columnId);
		
		model.addAttribute("columnDetail", columndetail);
		
		return "column/columnDetail";
	}
}
