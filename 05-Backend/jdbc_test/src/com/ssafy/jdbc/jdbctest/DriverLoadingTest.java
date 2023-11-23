package com.ssafy.jdbc.jdbctest;

public class DriverLoadingTest {
	
	public DriverLoadingTest() {
		//Driver Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Success!!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DriverLoadingTest();
	}

}
