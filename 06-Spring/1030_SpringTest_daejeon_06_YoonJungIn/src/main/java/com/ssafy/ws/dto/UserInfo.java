package com.ssafy.ws.dto;

import java.util.List;

public class UserInfo {
	private String id;
	private String pw;
	private String position;
	private String userNumber;
	private String name;
	private String rname;
	private int rclass;
	private List<Attendance> attendance;
	
	
	public UserInfo() {}
	
	

	public UserInfo(String id, String pw, String position, String userNumber, String name, String rname, int rclass,
			List<Attendance> attendance) {
		super();
		this.id = id;
		this.pw = pw;
		this.position = position;
		this.userNumber = userNumber;
		this.name = name;
		this.rname = rname;
		this.rclass = rclass;
		this.attendance = attendance;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getRclass() {
		return rclass;
	}

	public void setRclass(int rclass) {
		this.rclass = rclass;
	}

	public List<Attendance> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Attendance> attendance) {
		this.attendance = attendance;
	}



	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", pw=" + pw + ", position=" + position + ", userNumber=" + userNumber + ", name="
				+ name + ", rname=" + rname + ", rclass=" + rclass + ", attendance=" + attendance + "]";
	}
}
