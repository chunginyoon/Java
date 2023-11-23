package com.ssafy.car.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ssafy.car.dto.Member;
import com.ssafy.car.util.DBUtil;

@Repository
public class MemberDaoImpl implements MemberDao {

	private DBUtil dbUtil;

	public MemberDaoImpl(DBUtil dbUtil) {
		super();
		this.dbUtil = dbUtil;
	}

	@Override
	public Member login(Member member) throws SQLException {
		String sql = "select member_id, name, register_date\r\n" + "from member\r\n"
				+ "where member_id=? and password=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String registerDate = rs.getString("registerDate");

				Member memberInfo = new Member(member.getMemberId(), null, name, registerDate);
				return memberInfo;
			}
			return null;
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public void registerMember(Member member) throws SQLException {
		String sql = "insert into member(member_id, password, name)\r\n" + "values(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			int cnt = pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}
}