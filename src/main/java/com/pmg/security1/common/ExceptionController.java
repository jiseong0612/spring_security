package com.pmg.security1.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController implements ErrorController {
	
	@GetMapping("/error")
	public String handleError(HttpServletResponse response, HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (statusCode != null) {
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "error/404error";
			} else {
				return "error/error";
			}
		}
		return "error/error";
	}
}
