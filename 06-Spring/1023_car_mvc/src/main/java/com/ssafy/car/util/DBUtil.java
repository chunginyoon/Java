package com.ssafy.car.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DBUtil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/train";
		String user = "ssafy";
		String password = "ssafy";
		
		return DriverManager.getConnection(url, user, password);
	}
	
	public void close(AutoCloseable...autoCloseables ) {
		try {
			for(AutoCloseable obj : autoCloseables) {
				if(obj!=null) obj.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
