package com.ssafy.ws.model.dao;

import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ssafy.ws.dto.Member;

@Repository
public class MemberDaoImpl implements MemberMapper {

	private SqlSession sqlSession;

//	@Autowired	생략가능
	public MemberDaoImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Member login(Member member) throws SQLException {
		return sqlSession.selectOne("MemberMapper.login", member);
	}
}
