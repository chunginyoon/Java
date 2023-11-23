package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.Member;
import com.ssafy.model.dao.MemberDao;
import com.ssafy.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao = new MemberDaoImpl();
	
	
	@Override
	public Member login(Member member) throws SQLException {
		return memberDao.login(member);
	}

}
