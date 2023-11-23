package com.ssafy.car.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionController {

	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(SQLException.class)
	public String sqlException(SQLException e) {

		logger.debug("SQL 에러 메세지: {}", e.getMessage());

		return "error/500error";
	}
	
	@ExceptionHandler(Exception.class)
	public String allException(Exception e) {

		logger.debug("에러 메세지: {}", e.getMessage());

		return "error/500error";
	}
	

}
