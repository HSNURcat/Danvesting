package com.dandan.danvesting;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {
	
	@GetMapping("/error")
    public String handleError(HttpServletRequest request, String errorMessage) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object statusObjMsg = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        
        if(status != null){
            int statusCode = Integer.valueOf(status.toString());
            String statusMessage = statusObjMsg.toString();

            if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "errorpages/badRequest";
            } else {
            	return "errorpages/notFoundPage";
            }
        }
        return "errorpages/notFoundPage";
    }
}
