package com.ssafy.home.model.dao;

import java.sql.SQLException;

import com.ssafy.home.model.service.HouseDealService;
import com.ssafy.home.model.service.HouseDealServiceImpl;
import com.ssafy.util.DBUtil;

public class DongCodeDaoImpl implements DongCodeDao{
	private static DongCodeDao dongCodeDao = new DongCodeDaoImpl();
	private DBUtil dbUtil = DBUtil.getInstance();
	

	private DongCodeDaoImpl() {
		
	}
	
	public static DongCodeDao getInstance() {
		return dongCodeDao;
	}
	
	
	@Override
	public String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from dongcode\r\n" + 
				"where sidoName= ? and gugunName=? and dongName=?;";
		Connection conn = null;
		
		
		return null;
	}
}
