package com.ssafy.board.model.dao;

import com.ssafy.board.BoardDto;
import com.ssafy.board.util.DBUtil;
import java.util.*;
import java.sql.*;

public class BoardDao {
	private DBUtil dbUtil = DBUtil.getInstance();

	public List<BoardDto> searchListAll() throws SQLException {
		// 1. sql 작성
		String sql = "select * from board";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. DB 연결
			conn = dbUtil.getConnection();

			// 3. 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 4. 조회 결과 파싱
			List<BoardDto> list = new ArrayList<>();
			while (rs.next()) {
				int articleNo = rs.getInt("article_no");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String userId = rs.getString("user_id");
				int hit = rs.getInt("hit");
				String registerTime = rs.getString("register_time");

				BoardDto boardDto = new BoardDto();
				boardDto.setArticleNo(articleNo);
				boardDto.setSubject(subject);
				boardDto.setContent(content);
				boardDto.setUserId(userId);
				boardDto.setHit(hit);
				boardDto.setRegisterTime(registerTime);
				list.add(boardDto);
			}
			return list;

		} finally {
			// 5. 자원 반납
			dbUtil.close(rs, pstmt, conn);
		}
	}

	public void registerArticle(BoardDto boardDto) throws SQLException {
		// 1. sql 작성
		String sql = "insert into board\r\n" + "( user_id, subject, content )\r\n" + "values\r\n" + "( ? , ? , ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 2. DB 연결
			conn = dbUtil.getConnection();

			// 3. 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.executeUpdate();
		} finally {
			// 4. 자원 반납
			dbUtil.close(pstmt, conn);
		}
	}

	public BoardDto viewArticle(int no) throws SQLException {

		String sql = "select* from board where article_no=?";
		BoardDto boardDto = new BoardDto();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String userId = rs.getString("user_id");
				String registerTime = rs.getString("register_time");
				int hit = rs.getInt("hit");

				boardDto.setArticleNo(no);
				boardDto.setSubject(subject);
				boardDto.setContent(content);
				boardDto.setUserId(userId);
				boardDto.setHit(hit);
				boardDto.setRegisterTime(registerTime);
			}
			return boardDto;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}
}