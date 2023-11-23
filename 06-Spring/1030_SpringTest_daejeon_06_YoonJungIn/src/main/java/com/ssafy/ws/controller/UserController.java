package com.ssafy.ws.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ssafy.ws.dto.Attendance;
import com.ssafy.ws.dto.UserInfo;
import com.ssafy.ws.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserInfo userInfo, HttpSession session) {
		UserInfo loginInfo = userService.login(userInfo);

		if (loginInfo != null) {
			session.setAttribute("userInfo", loginInfo);
			System.out.println(loginInfo);
			return "redirect:/user/regist";
		}

		return "redirect:/";

	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/regist")
	public String mvRegist() {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute Attendance attendance) {
		userService.regist(attendance);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<UserInfo> list = userService.selectAll();
		for(UserInfo user:list)
			System.out.println(user);
		model.addAttribute("list", list);
		return "list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "ano") String ano, Model model) {
		Attendance attendance = userService.selectByAno(ano);
		model.addAttribute("attendance", attendance);
		return "detail";
	}
}
