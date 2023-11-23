package com.ssafy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.board.controller.BoardController;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.board.model.service.BoardServiceImpl;
import com.ssafy.util.DBUtil;

@Configuration
@ComponentScan(basePackages = { "com.ssafy" })
public class ApplicationConfig {

	@Bean
	public DBUtil dbUtil() {
		return new DBUtil();
	}

	@Bean
	public BoardDaoImpl boardDaoImpl() {
		return new BoardDaoImpl(dbUtil());
	}

	@Bean
	public BoardServiceImpl boardServiceImpl() {
		return new BoardServiceImpl(boardDaoImpl());
	}

	@Bean
	public BoardController boardController() {
		return new BoardController(boardServiceImpl());
	}
}