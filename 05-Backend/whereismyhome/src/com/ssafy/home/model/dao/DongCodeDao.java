package com.ssafy.home.model.dao;

import java.sql.SQLException;

public interface DongCodeDao {
	String getDongCode(String sidoName, String gugunName, String dongName) throws SQLException;
//	List<HouseDeal> select();
}
