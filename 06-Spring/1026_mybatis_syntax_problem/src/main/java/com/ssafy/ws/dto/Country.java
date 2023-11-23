package com.ssafy.ws.dto;

import java.util.List;

public class Country {
	private String code;
	private String name;
	private String continent;
	private String capital;
	//1:1관계 인 경우
	private City capitalInfo;	//수도 정보
	//1:N관계인 경우
	private List<CountryLanguage> languages;	//언어 정보

	
	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", capital=" + capital
				+ ", capitalInfo=" + capitalInfo + ", languages=" + languages + "]";
	}

	public Country() {}

	public Country(String code, String name, String continent, String capital, City capitalInfo,
			List<CountryLanguage> languages) {
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.capital = capital;
		this.capitalInfo = capitalInfo;
		this.languages = languages;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public City getCapitalInfo() {
		return capitalInfo;
	}

	public void setCapitalInfo(City capitalInfo) {
		this.capitalInfo = capitalInfo;
	}

	public List<CountryLanguage> getLanguages() {
		return languages;
	}

	public void setLanguages(List<CountryLanguage> languages) {
		this.languages = languages;
	}
	
	
}
