package com.ssafy.ws.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.ws.dto.Book;

@Repository
public class BookDaoImpl implements BookDao {

	private SqlSession sqlSession;

//	@Autowired	생략가능
	public BookDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<Book> selectAll() throws SQLException {
		return sqlSession.selectList("BookMapper.selectAll");
	}

	@Override
	public Book selectByNo(int no) throws SQLException {
		return sqlSession.selectOne("BookMapper.selectByNo", no);
	}

	@Override
	public int registBook(Book book) throws SQLException {
		return sqlSession.insert("BookMapper.registBook", book);
	}

	@Override
	public int deleteByNo(int no) throws SQLException {
		return sqlSession.delete("BookMapper.deleteByNo", no);
	}
}
