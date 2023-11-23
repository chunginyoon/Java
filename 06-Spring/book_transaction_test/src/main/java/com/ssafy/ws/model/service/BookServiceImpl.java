package com.ssafy.ws.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	private BookMapper bookMapper;
	
//	@Autowired 생략
	public BookServiceImpl(BookMapper bookDao) {
		this.bookMapper = bookDao;
	}
	
	@Override
	public List<Book> selectAll() throws SQLException {
		return bookMapper.selectAll();
	}

	@Override
	public Book selectByNo(int no) throws SQLException {
		return bookMapper.selectByNo(no);
	}

	@Override
	public int registBook(Book book) throws SQLException {
		return bookMapper.registBook(book);
	}

	@Override
	@Transactional
	public int deleteByNo(int no) throws SQLException {
		bookMapper.deleteByNo(3);
		bookMapper.deleteByNo(4);
		
		if(true) throw new NullPointerException();
		
		bookMapper.deleteByNo(5);
		
		return bookMapper.deleteByNo(no);
	}

}
