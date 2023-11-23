package com.ssafy.ws.dto;

public class Member {

	private int memberNo;
	private String memberId;
	private String password;
	private String name;
	private String registrationDate;
	
	public Member() {}

	public Member(int memberNo, String memberId, String password, String name, String registrationDate) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.password = password;
		this.name = name;
		this.registrationDate = registrationDate;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", password=" + password + ", name=" + name
				+ ", registrationDate=" + registrationDate + "]";
	}
	
	
}
