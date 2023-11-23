package com.ssafy.ws.model.service;

import java.sql.SQLException;


import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Member;
import com.ssafy.ws.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	
	public MemberServiceImpl(MemberMapper memberDao) {
		this.memberMapper = memberDao;
	}
	
	@Override
	public Member login(Member member) throws SQLException {
		return memberMapper.login(member);
	}

}
