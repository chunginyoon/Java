package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.Member;
import com.ssafy.util.DBUtil;

public class MemberDaoImpl implements MemberDao {

	private DBUtil dbUtil = DBUtil.getInstance();

	@Override
	public Member login(Member member) throws SQLException {
		//1. sql 작성
		String sql = "select member_id,name,register_date\r\n" + 
				"from member\r\n" + 
				"where member_id =? and password=?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//2. DB연결
			conn =dbUtil.getConnection();			
			//3. 쿼리 실행
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			rs=pstmt.executeQuery();

			//4. 조회 결과 파싱
			if(rs.next()) {
				String name=rs.getString("name");
				String registerDate=rs.getString("register_date");
			
				Member loginMember = new Member(member.getMemberId(),null,name,registerDate);
				return loginMember;
			}
			return null;

		}finally {
			dbUtil.close(rs,pstmt,conn);
		}
	}

}
