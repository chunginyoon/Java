package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssafy.ws.dto.City;
import com.ssafy.ws.dto.Country;
import com.ssafy.ws.model.mapper.WorldMapper;

@Controller
public class HomeController {
	
	private WorldMapper worldMapper;
	public HomeController(WorldMapper worldMapper) {
		this.worldMapper = worldMapper;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	/**
	 * 도시 정보 등록 후 등록된 도시의 ID log찍기
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		City city = new City(-1, "뉴도시", "KOR", "ㅇㅇ", 1000);
		worldMapper.insertCity(city);
		System.out.println("등록된 도시 정보의 ID : "+city.getId());
		
		return "home";
	}
	
	/**
	 * 마지막 도시 정보를 원하는 내용으로 수정(도시 번호는 수정 X)
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update() {
		City city = new City(-1, "수정된도시명", "ABW", "dd", 3000);
		worldMapper.updateLastCity(city);
		
		return "home";
	}
	
	/**
	 * 모든 나라의 나라 정보 및 수도 정보 조회(1:1 join)
	 * @return
	 */
	@RequestMapping(value = "/selectCoCi", method = RequestMethod.GET)
	public String selectCoCi() {
		
		List<Country> list = worldMapper.selectCoCi();
		for(Country country : list) {
			System.out.println(country);
		}
		
		return "home";
	}
	
	
	/**
	 * 모든 나라의 나라 정보 및 언어 정보 조회(1:N join)
	 * @return
	 */
	@RequestMapping(value = "/selectCoCl", method = RequestMethod.GET)
	public String selectCoCl() {
		
		List<Country> list = worldMapper.selectCoCl();
		for(Country country : list) {
			System.out.println(country);
		}
		
		return "home";
	}
	
}
