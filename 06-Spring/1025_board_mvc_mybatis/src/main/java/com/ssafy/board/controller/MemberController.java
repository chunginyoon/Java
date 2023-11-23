package com.ssafy.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.board.dto.Member;
import com.ssafy.board.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("login")
	public String mvLogin() {
		return "login";
	}

	@PostMapping("login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		Member loginInfo = memberService.login(member);

		if (loginInfo != null) {
			session.setAttribute("userInfo", loginInfo);
			return "redirect:/";
		}

		return "login";

	}

	@GetMapping("logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}
}