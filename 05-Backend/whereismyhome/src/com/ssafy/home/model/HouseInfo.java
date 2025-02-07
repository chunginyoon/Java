package com.ssafy.home.model;

public class HouseInfo {
	private String aptCode;
	private int buildYear;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private char roadNameBasementCode;
	private String roadNameCode;
	private String dong;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String dongCode;
	private char landCode;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
	public HouseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseInfo(String aptCode, int buildYear, String roadName, String roadNameBonbun, String roadNameBubun,
			char roadNameBasementCode, String roadNameCode, String dong, String bonbun, String bubun,
			String sigunguCode, String eubmyundongCode, String dongCode, char landCode, String apartmentName,
			String jibun, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameCode = roadNameCode;
		this.dong = dong;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.sigunguCode = sigunguCode;
		this.eubmyundongCode = eubmyundongCode;
		this.dongCode = dongCode;
		this.landCode = landCode;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "HouseInfo [aptCode=" + aptCode + ", buildYear=" + buildYear + ", roadName=" + roadName
				+ ", roadNameBonbun=" + roadNameBonbun + ", roadNameBubun=" + roadNameBubun + ", roadNameBasementCode="
				+ roadNameBasementCode + ", roadNameCode=" + roadNameCode + ", dong=" + dong + ", bonbun=" + bonbun
				+ ", bubun=" + bubun + ", sigunguCode=" + sigunguCode + ", eubmyundongCode=" + eubmyundongCode
				+ ", dongCode=" + dongCode + ", landCode=" + landCode + ", apartmentName=" + apartmentName + ", jibun="
				+ jibun + ", lng=" + lng + ", lat=" + lat + "]";
	}
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}
	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}
	public String getRoadNameBubun() {
		return roadNameBubun;
	}
	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}
	public char getRoadNameBasementCode() {
		return roadNameBasementCode;
	}
	public void setRoadNameBasementCode(char roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}
	public String getRoadNameCode() {
		return roadNameCode;
	}
	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getBonbun() {
		return bonbun;
	}
	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}
	public String getBubun() {
		return bubun;
	}
	public void setBubun(String bubun) {
		this.bubun = bubun;
	}
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getEubmyundongCode() {
		return eubmyundongCode;
	}
	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public char getLandCode() {
		return landCode;
	}
	public void setLandCode(char landCode) {
		this.landCode = landCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
}
