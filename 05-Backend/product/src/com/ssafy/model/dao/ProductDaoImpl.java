package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.Product;
import com.ssafy.util.DBUtil;

public class ProductDaoImpl implements ProductDao {

	private DBUtil dbUtil = DBUtil.getInstance();

	@Override
	public List<Product> selectAll() throws SQLException {
		String sql = "select *\r\n" + "from product";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Product> products = new ArrayList<>();

			while (rs.next()) {
				String code = rs.getString("code");
				String model = rs.getString("model");
				int price = rs.getInt("price");

				Product product = new Product();
				product.setCode(code);
				product.setModel(model);
				product.setPrice(price);

				products.add(product);
			}
			return products;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public Product selectByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub

	}

	@Override
	public void register(Product product) {
		// TODO Auto-generated method stub

	}

}
