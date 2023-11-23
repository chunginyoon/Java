package com.ssafy.board.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.board.dto.Member;
import com.ssafy.board.model.mapper.MemberMapper;

@Service
public class MemberServiceImple implements MemberService {

	private MemberMapper memberMapper;
	
	public MemberServiceImple(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public Member login(Member member) {
		return memberMapper.login(member);
	}
}