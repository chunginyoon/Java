package com.ssafy.car.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.car.dto.Member;
import com.ssafy.car.model.dao.MemberDao;
@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	
	
	public MemberServiceImpl(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}


	@Override
	public Member login(Member member) throws SQLException {
		return memberDao.login(member);
	}


	@Override
	public void registerMember(Member member) throws SQLException {
		memberDao.registerMember(member);
		
	}
}