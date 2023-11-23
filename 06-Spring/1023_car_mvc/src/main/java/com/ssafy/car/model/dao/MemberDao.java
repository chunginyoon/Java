package com.ssafy.car.model.dao;

import java.sql.SQLException;

import com.ssafy.car.dto.Member;

public interface MemberDao {
	Member login(Member member) throws SQLException;

	void registerMember(Member member) throws SQLException;
}
