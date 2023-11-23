package com.ssafy.ws.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Member;
import com.ssafy.ws.model.dao.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	MemberMapper memberDao;
	
	public MemberServiceImpl(MemberMapper memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public Member login(Member member) throws SQLException {
		return memberDao.login(member);
	}

}
