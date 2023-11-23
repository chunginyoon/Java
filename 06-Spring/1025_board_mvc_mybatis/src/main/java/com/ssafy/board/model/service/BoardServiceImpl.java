package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.board.dto.Product;
import com.ssafy.board.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;

	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public List<Product> selectAll() {
		return boardMapper.selectAll();
	}

	@Override
	public Product selectByCode(String code) {
		return boardMapper.selectByCode(code);
	}

	@Override
	public int regist(Product product) {
		return boardMapper.regist(product);
	}

	@Override
	public int deleteByCode(String code) {
		return boardMapper.deleteByCode(code);
	}
}