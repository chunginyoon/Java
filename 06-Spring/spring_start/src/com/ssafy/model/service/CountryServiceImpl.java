package com.ssafy.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.Country;
import com.ssafy.model.dao.CountryDao;
import com.ssafy.model.dao.CountryDaoImpl;

public class CountryServiceImpl implements CountryService {

	private CountryDao countryDao;

//	public CountryServiceImpl(CountryDao countryDao) {
//		this.countryDao = countryDao;
//	}
	

	public void setCountryDao(CountryDao countryDao) {
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
