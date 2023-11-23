package com.ssafy.ws.model.service;

import java.sql.SQLException;

import com.ssafy.ws.dto.Member;

public interface MemberService {

	Member login(Member member) throws SQLException;
}
