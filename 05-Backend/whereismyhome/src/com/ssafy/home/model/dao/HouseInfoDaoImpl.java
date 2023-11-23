package com.ssafy.home.model.dao;

import com.ssafy.home.model.service.HouseDealService;
import com.ssafy.home.model.service.HouseDealServiceImpl;
import com.ssafy.util.DBUtil;

public class HouseInfoDaoImpl implements HouseInfoDao{
	private static HouseInfoDao houseInfoDao = new HouseInfoDaoImpl();
	private DBUtil dbUtil = DBUtil.getInstance();
	private HouseInfoDaoImpl() {
		
	}
	
	public static HouseInfoDao getInstance() {
		return houseInfoDao;
	}
}
