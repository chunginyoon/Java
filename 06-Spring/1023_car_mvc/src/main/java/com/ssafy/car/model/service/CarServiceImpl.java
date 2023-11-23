package com.ssafy.car.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.car.dto.Car;
import com.ssafy.car.model.dao.CarDao;

@Service
public class CarServiceImpl implements CarService {
	private CarDao carDao;
	
//	@Autowired 생략
	public CarServiceImpl(CarDao carDao) {
		this.carDao = carDao;
	}
	
	@Override
	public List<Car> selectAll() throws SQLException {
		return carDao.selectAll();
	}

	@Override
	public Car selectByCarNo(int carNo) throws SQLException {
		return carDao.selectByCarNo(carNo);
	}

	@Override
	public int registCar(Car car) throws SQLException {
		return carDao.registCar(car);
	}

	@Override
	public int deleteCar(int carNo) throws SQLException {
		return carDao.deleteCar(carNo);
		
	}
}