package com.ssafy.home.model.dao;

import com.ssafy.home.model.service.HouseDealService;
import com.ssafy.home.model.service.HouseDealServiceImpl;
import com.ssafy.util.DBUtil;

public class HouseDealDaoImpl implements HouseDealDao{
	private static HouseDealDao houseDealDao = new HouseDealDaoImpl();
	private DBUtil dbUtil = DBUtil.getInstance();
	private HouseDealDaoImpl() {
		
	}
	
	public static HouseDealDao getInstance() {
		return houseDealDao;
	}
}
