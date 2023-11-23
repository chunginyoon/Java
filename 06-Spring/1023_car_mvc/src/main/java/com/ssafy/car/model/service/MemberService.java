package com.ssafy.car.model.service;

import java.sql.SQLException;
import com.ssafy.car.dto.Member;

public interface MemberService {
	Member login(Member member) throws SQLException;
	
	void registerMember(Member member) throws SQLException;
}
