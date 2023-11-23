package com.ssafy;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssafy.model.service.CountryService;

public class test {

    public static void main(String[] args) throws SQLException {
        //나라 목록 정보 조회
        
//        CountryService countryService = new CountryServiceImpl();
//        List<Country> list = countryService.selectAll();
//        
//        for(Country country: list) System.out.println(list);
        
        ApplicationContext context = 
                new ClassPathXmlApplicationContext("com/ssafy/application-context.xml");
        
//        DBUtil dbUtil = context.getBean(DBUtil.class);
//        Connection conn = dbUtil.getConnection();
//        
//        CountryDao countryDao = context.getBean(CountryDao.class);
//        List<Country> list = countryDao.selectAll();
//        for(Country country: list) System.out.println(country);
        
        CountryService countryService = context.getBean(CountryService.class);
        countryService.selectAll();
    }

}
