package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.dto.Product;

public interface BoardService {

	List<Product> selectAll();

	Product selectByCode(String code);

	int regist(Product product);

	int deleteByCode(String code);
}
