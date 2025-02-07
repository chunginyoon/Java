package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.model.BoardDto;

public interface BoardDao {

	void registerArticle(BoardDto boardDto) throws SQLException;
	List<BoardDto> searchListAll() throws SQLException;
	List<BoardDto> searchListBySubject(String subject) throws SQLException;
	BoardDto viewArticle(int no) throws SQLException;
	void modifyArticle(BoardDto boardDto) throws SQLException;
	void deleteArticle(int no) throws SQLException;
	
}
