package com.ssafy.vue.member.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.member.dto.MemberDto;
import com.ssafy.vue.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody MemberDto member){
		log.debug("login() 요청 수신, {}", member);
		Map<String, Object> loginInfo = memberService.loginMember(member);
		
		//로그인 실패 시 401응답
		if(loginInfo==null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); 
		
		return ResponseEntity.ok(loginInfo);
	}
	
}
