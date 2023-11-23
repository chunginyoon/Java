package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

/**
 * 
 * mysql 드라이버 클래스 로딩
 * 
 * Connection 객체 반환 기능
 * 
 * @author SSAFY
 *
 */
@Component(value = "util")
public class DbUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/world";
		String user = "ssafy";
		String password = "ssafy";
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public void close(AutoCloseable...autoCloseables) {
		for(AutoCloseable obj : autoCloseables) {
			if(obj!=null)
				try {
					obj.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}
