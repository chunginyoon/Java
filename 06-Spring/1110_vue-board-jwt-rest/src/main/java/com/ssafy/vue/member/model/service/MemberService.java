package com.ssafy.vue.member.model.service;

import java.util.Map;

import com.ssafy.vue.member.dto.MemberDto;

public interface MemberService {

	Map<String, Object> loginMember(MemberDto member);
	
}
