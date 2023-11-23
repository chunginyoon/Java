package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.Book;

@Mapper
public interface BookMapper {

	List<Book> selectAll();
	
	Book selectByNo(int no);
	
	int registBook(Book book);
	
	int deleteByNo(int no);
}
