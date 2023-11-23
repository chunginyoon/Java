package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.dto.Car;
@Mapper
public interface CarMapper {

	List<Car> selectAll();
	
	Car selectByCarNo(int carNo);
	
	int regist(Car car);
	
	int modify(Car car);
	
	int delete(int carNo);
}
