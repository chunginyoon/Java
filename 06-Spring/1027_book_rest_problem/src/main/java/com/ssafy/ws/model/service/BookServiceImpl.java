package com.ssafy.ws.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {
	private BookMapper bookMapper;
	
//	@Autowired 생략
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	
	@Override
	public List<Book> selectAll()  {
		return bookMapper.selectAll();
	}

	@Override
	public Book selectByNo(int no)  {
		return bookMapper.selectByNo(no);
	}

	@Override
	public int registBook(Book book)  {
		return bookMapper.registBook(book);
	}

	@Override
	public int deleteByNo(int no)  {
		return bookMapper.deleteByNo(no);
	}

}
