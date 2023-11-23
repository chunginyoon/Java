package com.ssafy.car.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.car.dto.Car;
import com.ssafy.car.util.DBUtil;

@Repository
public class CarDaoImpl implements CarDao {

	private DBUtil dbUtil;

//	@Autowired	생략가능
	public CarDaoImpl(DBUtil dbUtil) {
		this.dbUtil = dbUtil;
	}

	@Override
	public List<Car> selectAll() throws SQLException {
		// 1. sql 작성
		String sql = "select car_no, number, model, price, brand\r\n" + "from car";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2. DB 연결
			conn = dbUtil.getConnection();

			// 3. 쿼리실행
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 4. 조회 결과 파싱 후 반환
			List<Car> list = new ArrayList<Car>();
			while (rs.next()) {
				int carNo = rs.getInt("car_no");
				String number = rs.getString("number");
				String model = rs.getString("model");
				int price = rs.getInt("price");
				String brand = rs.getString("brand");
				Car car = new Car(carNo, number, model, price, brand);
				list.add(car);
			}
			return list;

		} finally {
			// 5. 자원 반납
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public Car selectByCarNo(int carNo) throws SQLException {
		String sql = "\r\n" + "select car_no, number, model, price, brand\r\n" + "from car\r\n" + "where car_no = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 2. DB 연결
			conn = dbUtil.getConnection();

			// 3. 쿼리실행
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, carNo);
			rs = pstmt.executeQuery();

			// 4. 조회 결과 파싱 후 반환
			if (rs.next()) {
				String number = rs.getString("number");
				String model = rs.getString("model");
				int price = rs.getInt("price");
				String brand = rs.getString("brand");
				Car car = new Car(carNo, number, model, price, brand);
				return car;
			}
			return null;
		} finally {
			// 5. 자원 반납
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public int registCar(Car car) throws SQLException {
		String sql = "insert into car\r\n" + "(number, model, price, brand)\r\n" + "values \r\n" + "(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car.getNumber());
			pstmt.setString(2, car.getModel());
			pstmt.setInt(3, car.getPrice());
			pstmt.setString(4, car.getBrand());
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int deleteCar(int carNo) throws SQLException {
		
		String sql = "delete from car\r\n" + "where car_no=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, carNo);
			int cnt = pstmt.executeUpdate();
			
			return cnt;
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}