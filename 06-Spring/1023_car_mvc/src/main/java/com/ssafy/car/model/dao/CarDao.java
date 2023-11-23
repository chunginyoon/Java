package com.ssafy.car.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.car.dto.Car;

public interface CarDao {

	List<Car> selectAll() throws SQLException;

	Car selectByCarNo(int carNo) throws SQLException;

	int registCar(Car car) throws SQLException;

	int deleteCar(int carNo) throws SQLException;

}
