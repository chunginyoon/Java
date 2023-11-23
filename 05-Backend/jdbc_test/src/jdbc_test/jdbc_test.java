package jdbc_test;

import java.sql.*;
import java.io.*;
import java.util.*;
import com.mysql.cj.protocol.Resultset;

/*
 * 1. Driver 클래스 로딩
 * 2. Connection 얻어오기
 * 3. SQL 작성
 * 4. 쿼리문 보내기(실행) (select절의 경우)
 * 5. 조회 결과를 원하는 데이터 형태로 가공
 */
public class jdbc_test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 1. Driver 클래스 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 2. Connection 얻어오기
		String url = "jdbc:mysql://localhost:3306/world";
		String user = "ssafy";
		String pwd = "ssafy";
		Connection conn = DriverManager.getConnection(url, user, pwd);

		// 3. SQL 작성
		String sql = "select *from country limit 10";

		// 4. 쿼리문 보내기(실행) (select절의 경우)
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		System.out.println(rs);
		
		// 5. 조회 결과를 원하는 데이터 형태로 가공
		List<Country> countries = new ArrayList<>();
		while (rs.next()) {
			String code = rs.getString(1);
			String name = rs.getString(2);
			String continent = rs.getString(3);
			double gnp = rs.getDouble("gnp");

			System.out.printf("%s %s %s %f\n", code, name, continent, gnp);
			Country country = new Country(code, name, continent, gnp);
			countries.add(country);
		}

		for (Country country : countries)
			System.out.println(country);
	}
}
