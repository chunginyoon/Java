package com.ssafy.home.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.home.model.HouseDeal;

public interface HouseDealService {
	List<HouseDeal> select(String sidoName, String gugunName, String dongName, int year, int month) throws SQLException;
}
