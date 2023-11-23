package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.Attendance;
import com.ssafy.ws.dto.UserInfo;

public interface UserService {
	UserInfo login(UserInfo userInfo);
	
	int regist(Attendance attendance);
	
	List<UserInfo> selectAll();
	
	Attendance selectByAno(String ano);
}
