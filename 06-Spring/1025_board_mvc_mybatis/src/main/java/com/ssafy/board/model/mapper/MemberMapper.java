package com.ssafy.board.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
}
