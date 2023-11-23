package com.ssafy.car.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.car.dto.Car;

public interface CarDao {
	/**
	 * 전체 조회
	 */
	List<Car> selectAll() throws SQLException;
	
	/**
	 * 상세 조회
	 */
	Car selectByCarNo(int carNo) throws SQLException;
	
	/**
	 * 자동차 등록
	 */
	int registCar(Car car) throws SQLException;
}
