package com.ssafy.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {
	
	@Bean
	@Scope("prototype")
	public DBUtil dbUtil() {
		return new DBUtil();
	}
}
