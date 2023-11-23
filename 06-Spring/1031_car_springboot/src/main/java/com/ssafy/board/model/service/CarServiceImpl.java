package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.board.dto.Car;
import com.ssafy.board.model.mapper.CarMapper;
@Service
public class CarServiceImpl implements CarService{
	
	private CarMapper carMapper;
	
	public CarServiceImpl(CarMapper carMapper) {
		super();
		this.carMapper = carMapper;
	}

	@Override
	public List<Car> selectAll() {
		return carMapper.selectAll();
	}

	@Override
	public Car selectByCarNo(int carNo) {
		return carMapper.selectByCarNo(carNo);
	}

	@Override
	public int regist(Car car) {
		return carMapper.regist(car);
	}

	@Override
	public int modify(Car car) {
		return carMapper.modify(car);
	}

	@Override
	public int delete(int carNo) {
		return carMapper.delete(carNo);
	}

}
