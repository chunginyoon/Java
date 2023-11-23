package com.ssafy.jdbc;

import java.sql.SQLException;
import java.util.List;
import com.ssafy.jdbc.model.dao.CountryDao;

public class MainTest {

	public static void main(String[] args) throws SQLException {
		CountryDao countryDao = new CountryDao();

		List<Country> list = countryDao.selectAll();
//		for(Country cty:list)
//			System.out.println(cty);

		// 추가 테스트
		Country country = new Country("FFF", "나라2", "Asia", 3333);
		int cnt = countryDao.insertCountry(country);
		System.out.println("추가 후 변경된 row 수: " + cnt);

		// 수정 테스트
		country = new Country("FFF", "수정됨", "Asia", 111111);
		cnt = countryDao.updateCountry(country);
		System.out.println("수정 후 변경된 row 수: " + cnt);

		// 삭제 테스트
		cnt = countryDao.deleteByCode("DDD");
		System.out.println("삭제 후 변경된 row 수: " + cnt);

		country = countryDao.seletByCode("ABW");
	}
}
