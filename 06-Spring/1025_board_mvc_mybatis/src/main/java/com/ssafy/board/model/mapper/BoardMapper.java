package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.dto.Product;

@Mapper
public interface BoardMapper {
	
	List<Product> selectAll();

	Product selectByCode(String code);

	int regist(Product product);

	int deleteByCode(String code);
}
