package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.Country;
import com.ssafy.model.dao.CountryDao;

@Service
public class CountryServiceImpl implements CountryService {

	private CountryDao countryDao;
	
	@Autowired
	public CountryServiceImpl(
			@Qualifier(value = "countryDaoImpl2"
			) CountryDao countryDao ) {
		System.out.println("서비스 생성자 호출");
		this.countryDao = countryDao;
	}
	
	@Override
	public List<Country> selectAll() throws SQLException {
		System.out.println("Service의 selectAll() 실행");
		return countryDao.selectAll();
	}

	@Override
	public int insertCountry(Country country) throws SQLException {
		return countryDao.insertCountry(country);
	}

	@Override
	public int updateCountry(Country country) throws SQLException {
		return countryDao.updateCountry(country);
	}

	@Override
	public int deleteByCode(String code) throws SQLException {
		return countryDao.deleteByCode(code);
	}

	@Override
	public Country selectByCode2(String code) throws SQLException {
		return countryDao.selectByCode2(code);
	}

}
