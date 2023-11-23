package com.ssafy.board.util;

import java.sql.*;

public class DBUtil {

	// 싱글톤 패턴
	// 1. 생성자 막기
	// 2. 인스턴스 생성
	// 3. 인스턴스 변환하는 메소드 만들기

	private DBUtil() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static DBUtil instance = new DBUtil();

	public static DBUtil getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/ssafyweb";
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
				}
		}
	}
}
