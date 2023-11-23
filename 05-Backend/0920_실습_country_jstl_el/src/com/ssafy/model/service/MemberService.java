package com.ssafy.model.service;

import java.sql.SQLException;

import com.ssafy.Member;

public interface MemberService {
	Member login(Member member) throws SQLException;//아이디,비번 입력-아이디,등록일,이름 반환
	
}
