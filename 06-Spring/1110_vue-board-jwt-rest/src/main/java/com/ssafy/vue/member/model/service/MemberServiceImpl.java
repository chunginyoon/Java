package com.ssafy.vue.member.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.member.dto.MemberDto;
import com.ssafy.vue.member.model.mapper.MemberMapper;
import com.ssafy.vue.util.JWTUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> loginMember(MemberDto member) {
		
		MemberDto loginUser = memberMapper.loginMember(member);
		
		//해당 유저 정보 없음
		if(loginUser==null) return null;
		
		//토큰 생성
		String token = JWTUtil.generateToken(loginUser);
		
		//응답 데이터에 토큰 정보 구성
		Map<String, Object> tokenResponse = new HashMap<>();
		tokenResponse.put("token", token);
		
		return tokenResponse;
	}

}
