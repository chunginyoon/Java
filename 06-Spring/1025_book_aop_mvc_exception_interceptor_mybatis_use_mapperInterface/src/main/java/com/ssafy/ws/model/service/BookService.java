package com.ssafy.ws.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.ws.dto.Book;

public interface BookService {
	/**
	 * 전체 조회
	 */
	List<Book> selectAll() throws SQLException;
	
	/**
	 * 상세 조회
	 */
	Book selectByNo(int no) throws SQLException;
	
	/**
	 * 책 등록
	 */
	int registBook(Book book) throws SQLException;
	
	/**
	 * 책 삭제
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	int deleteByNo(int no) throws SQLException;
}
