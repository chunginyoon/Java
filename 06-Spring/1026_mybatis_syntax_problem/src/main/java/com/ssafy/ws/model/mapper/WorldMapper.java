package com.ssafy.ws.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.ws.dto.City;
import com.ssafy.ws.dto.Country;

public interface WorldMapper {
	int insertCity(City ci);
	
	int updateLastCity(City city);
	
	List<Country> selectCoCi();
	
	List<Country> selectCoCl();
	
}
