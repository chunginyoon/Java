package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.model.BoardDto;
import com.ssafy.jdbc.Country;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao {

	private static BoardDao boardDao = new BoardDaoImpl();

	private BoardDaoImpl() {
	}

	public static BoardDao getBoardDao() {
		return boardDao;
	}

	private DBUtil dbUtil = DBUtil.getInstance();

	@Override
	public void registerArticle(BoardDto boardDto) throws SQLException {
		String sql = "insert into BoardDto\n" + "(articleNo, subject, content, userId, registerTime)\n" + "values\n"
				+ "(?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardDto.getArticleNo());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			pstmt.setString(4, boardDto.getUserId());
			pstmt.setString(5, boardDto.getRegisterTime());

		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<BoardDto> searchListAll() throws SQLException {
		List<BoardDto> list = new ArrayList<BoardDto>();

		String sql = "select*\n" + "from BoardDto;";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String userId = rs.getString("userId");
				String registerTime = rs.getString("registerTime");

				BoardDto boardDto = new BoardDto(articleNo, subject, content, userId, registerTime);
				list.add(boardDto);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) throws SQLException {
		List<BoardDto> list = new ArrayList<BoardDto>();

		String sql = "select*\n" + "from BoardDto\n" + "where subject = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int articleNo = rs.getInt("articleNo");
				String content = rs.getString("content");
				String userId = rs.getString("userId");
				String registerTime = rs.getString("registerTime");

				BoardDto boardDto = new BoardDto(articleNo, subject, content, userId, registerTime);
				list.add(boardDto);
			}
			return list;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public BoardDto viewArticle(int no) throws SQLException {
		BoardDto boardDto = null;

		String sql = "select*\n" + "from BoardDto\n" + "where articleNo = ?";

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
				String userId = rs.getString("userId");
				String registerTime = rs.getString("registerTime");

				boardDto = new BoardDto(no, subject, content, userId, registerTime);
			}
			return boardDto;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		String sql = "update BoardDto\r\n" + 
                "set subject=?, content=?, userId=?, registerTime=?\r\n" + 
                "where articleNo=?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	
            conn = dbUtil.getConnection();
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, boardDto.getSubject() );
            pstmt.setString(2, boardDto.getContent() );
            pstmt.setString(3, boardDto.getUserId());
            pstmt.setString(4, boardDto.getRegisterTime());
     
        } finally {
            dbUtil.close(pstmt, conn);
        }
	}

	@Override
	public void deleteArticle(int no) throws SQLException {
		String sql = "delete from BoardDto\n" + 
				"where articleNo=?";
		try( 
                //2. db 연결
                Connection conn = dbUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ) {
            
            //3. 쿼리 실행
            pstmt.setInt(1, no);
        } 
	}
}