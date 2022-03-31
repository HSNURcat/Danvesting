package com.dandan.danvesting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dandan.danvesting.bo.DanvestingBO;
import com.dandan.danvesting.model.MainPageData;

@Controller
public class DanvestingController {
	
	@Autowired
	private DanvestingBO danvestingBO;
	
	@GetMapping("/main")
	public String mainPage(Model model) {
		
		MainPageData mainPageData = danvestingBO.getMainPageData();
		
		model.addAttribute("mainPageData", mainPageData);
		
		return "main";
	}
	
//	@GetMapping("/not_found")
//	public String notfound() {
//		return "errorpages/notFoundPage";
//	}
	
//	@GetMapping("/indices/**")
//	public String notfound2() {
//		return "errorpages/notFoundPage";
//	}
//	
//	@GetMapping("/commodities/**")
//	public String notfound3() {
//		return "errorpages/notFoundPage";
//	}
//	
//	@GetMapping("/economic-calendar/**")
//	public String notfound4() {
//		return "errorpages/notFoundPage";
//	}
//	@GetMapping("/equities/**")
//	public String notfound5() {
//		return "errorpages/notFoundPage";
//	}
//	@GetMapping("/rates-bonds/**")
//	public String notfound6() {
//		return "errorpages/notFoundPage";
//	}
//	@GetMapping("/currencies/**")
//	public String notfound7() {
//		return "errorpages/notFoundPage";
//	}
//	@GetMapping("/etfs/**")
//	public String notfound8() {
//		return "errorpages/notFoundPage";
//	}
//	@GetMapping("/crypto/**")
//	public String notfound9() {
//		return "errorpages/notFoundPage";
//	}
}
