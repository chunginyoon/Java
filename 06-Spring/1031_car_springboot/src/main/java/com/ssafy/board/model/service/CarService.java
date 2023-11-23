package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.dto.Car;

public interface CarService {

	List<Car> selectAll();

	Car selectByCarNo(int carNo);

	int regist(Car car);

	int modify(Car car);

	int delete(int carNo);
}
