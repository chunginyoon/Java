package com.ssafy.ws.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cors")
@CrossOrigin("http://127.0.0.1:5500")
public class CORSController {
	
	@ResponseBody
	@GetMapping
	public void test01() {
		System.out.println("test01() 실행");
	}
	
	@ResponseBody
	@GetMapping("/test02")
	public String test02(HttpServletResponse response) {
		
		response.setHeader("Access-Controll-Allow-Origin", "http://127.0.0.1:5500");
		return "hi";
	}
	
	@ResponseBody
	@GetMapping("/test03")
	public String test03() {
		return "hi";
	}
}
