package com.ssafy.ws.dto;

public class Attendance {

	private String ano;
	private String userNumber;
	private String issueDate;
	private String issue;
	
	public Attendance() {}
	
	public Attendance(String ano, String userNumber, String issueDate, String issue) {
		super();
		this.ano = ano;
		this.userNumber = userNumber;
		this.issueDate = issueDate;
		this.issue = issue;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Override
	public String toString() {
		return "Attendance [ano=" + ano + ", userNumber=" + userNumber + ", issueDate=" + issueDate + ", issue=" + issue
				+ "]";
	}
}
