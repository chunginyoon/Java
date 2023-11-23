package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

public class BoardServiceImpl implements BoardService {

	private static BoardService boardService = new BoardServiceImpl();
	private BoardDao boardDao;

	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getBoardDao();
	}

	public static BoardService getBoardService() {
		return boardService;
	}

	@Override
	public void writeArticle(BoardDto boardDto) throws SQLException {
		boardDao.writeArticle(boardDto);
	}

	@Override
	public List<BoardDto> listArticle() throws SQLException {
		return boardDao.listArticle();
	}

	@Override
	public int getTotalArticleCount() throws SQLException {

		return boardDao.getTotalArticleCount();
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		return boardDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		boardDao.updateHit(articleNo);

	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		boardDao.modifyArticle(boardDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		boardDao.deleteArticle(articleNo);
	}
}
