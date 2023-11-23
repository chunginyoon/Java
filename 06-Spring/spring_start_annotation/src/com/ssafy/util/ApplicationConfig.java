package com.ssafy.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ssafy.model.dao.CountryDaoImpl;
import com.ssafy.model.service.CountryServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.ssafy")
public class ApplicationConfig {

}
