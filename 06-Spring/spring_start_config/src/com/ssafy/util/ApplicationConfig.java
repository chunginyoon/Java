package com.ssafy.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ssafy.model.dao.CountryDaoImpl;
import com.ssafy.model.service.CountryServiceImpl;

@Configuration
public class ApplicationConfig {

	@Bean
	public DBUtil dbUtil() {
		return new DBUtil();
	}

	@Bean
	public CountryDaoImpl countryDaoImpl() {
		return new CountryDaoImpl(dbUtil());
	}
	
	@Bean
	public CountryServiceImpl countryServiceImpl() {
		return new CountryServiceImpl(countryDaoImpl());
	}
}
