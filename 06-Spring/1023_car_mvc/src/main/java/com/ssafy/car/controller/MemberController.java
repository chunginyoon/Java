package com.ssafy.car.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.car.dto.Member;
import com.ssafy.car.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/login")
	public String mvLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute Member member) throws SQLException {

		Member memberInfo = memberService.login(member);
		if (memberInfo != null) {
			return "redirect:/car/index";
		}

		else
			return "member/login";

	}

	@GetMapping("/logout")
	public String logout() {

		return "redirect:/car/index";
	}

	@GetMapping("/join")
	public String mvJoin() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute Member member) throws SQLException {
		memberService.registerMember(member);
		return "redirect:/car/index";
	}
}
