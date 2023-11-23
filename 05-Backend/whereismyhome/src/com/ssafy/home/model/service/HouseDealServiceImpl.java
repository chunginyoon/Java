package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.HouseDeal;
import com.ssafy.home.model.dao.DongCodeDao;
import com.ssafy.home.model.dao.DongCodeDaoImpl;
import com.ssafy.home.model.dao.HouseDealDao;
import com.ssafy.home.model.dao.HouseDealDaoImpl;
import com.ssafy.home.model.dao.HouseInfoDao;
import com.ssafy.home.model.dao.HouseInfoDaoImpl;

public class HouseDealServiceImpl implements HouseDealService{
	private static HouseDealService houseDealService = new HouseDealServiceImpl();
	
	private HouseDealServiceImpl() {
		
	}
	
	public static HouseDealService getInstance() {
		return houseDealService;
	}
	
	private HouseDealDao houseDealDao = HouseDealDaoImpl.getInstance();
	private HouseInfoDao houseInfoDao = HouseInfoDaoImpl.getInstance();
	private DongCodeDao dongCodeDao = DongCodeDaoImpl.getInstance();

	@Override
	public List<HouseDeal> select(String sidoName, String gugunName, String dongName, int year, int month)
			throws SQLException {
		// TODO Auto-generated method stub
		String dongCode = dongCodeDao.getDongCode(sidoName, gugunName, dongName);
		String aptCode = houseInfoDao.getAptCode(dongCode);
		List<HouseDeal> list = houseDealDao.getTradingHistroy(aptCode, year, month);
		return list;
	}
	
	
	

}
