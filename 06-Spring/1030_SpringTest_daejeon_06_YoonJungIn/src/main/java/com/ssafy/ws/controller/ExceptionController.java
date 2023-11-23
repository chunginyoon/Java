package com.ssafy.ws.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String allException(Exception e) {
		
		e.printStackTrace();
		
		return "error";
	}
	
}
