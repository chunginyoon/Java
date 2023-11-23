package com.ssafy.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.Product;

public interface ProductDao {
	
	public List<Product> selectAll() throws SQLException;

	public Product selectByCode(String code);

	public void delete(String code);

	public void register(Product product);
}
