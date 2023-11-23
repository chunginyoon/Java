package com.ssafy.ws.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
}
