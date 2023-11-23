package com.ssafy.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.ssafy.book.dto.Book;

@Repository
public class BookDaoImpl implements BookDao {

	private DataSource ds;
	
	
//	@Autowired	생략가능
	public BookDaoImpl(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Book> selectAll() throws SQLException {
		//1. sql 작성
		String sql = "select no, title, author, price\r\n" + 
				"from book";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//2. DB 연결
			conn = ds.getConnection();
			
			//3. 쿼리실행
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//4. 조회 결과 파싱 후 반환
			List<Book> list = new ArrayList<>();
			while(rs.next()){
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				Book book = new Book(no, title, author, price, null);
				list.add(book);
			}
			return list;
			
		} finally {
			//5. 자원 반납
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}

	@Override
	public Book selectByNo(int no) throws SQLException {
		String sql = "\r\n" + 
				"select no, title, author, price, content\r\n" + 
				"from book\r\n" + 
				"where no = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//2. DB 연결
			conn = ds.getConnection();
			
			//3. 쿼리실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			//4. 조회 결과 파싱 후 반환
			if(rs.next()){
				String title = rs.getString("title");
				String author = rs.getString("author");
				int price = rs.getInt("price");
				String content = rs.getString("content");
				Book book = new Book(no, title, author, price, content);
				return book;
			}
			return null;
		} finally {
			//5. 자원 반납
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}

	@Override
	public int registBook(Book book) throws SQLException {
		String sql = "insert into book\r\n" + 
				"(title, author, price, content)\r\n" + 
				"values \r\n" + 
				"(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			pstmt.setInt(3, book.getPrice());
			pstmt.setString(4, book.getContent());
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}

	@Override
	public int deleteByNo(int no) throws SQLException {
		String sql = "delete from book\r\n"
				+ "where no=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}
	}
}
