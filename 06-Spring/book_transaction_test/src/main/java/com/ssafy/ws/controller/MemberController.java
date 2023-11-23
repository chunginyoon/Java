package com.ssafy.ws.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.dto.Member;
import com.ssafy.ws.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	 private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member member, HttpSession session) throws SQLException {
		Member loginInfo = memberService.login(member);
		
		if(loginInfo!=null) {
			session.setAttribute("userInfo", loginInfo);
			return "redirect:/";
		}

		return "member/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws SQLException {
		
		session.invalidate();

		return "redirect:/";
	}
}
