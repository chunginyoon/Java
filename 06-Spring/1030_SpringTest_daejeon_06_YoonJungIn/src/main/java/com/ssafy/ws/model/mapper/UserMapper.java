package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.dto.Attendance;
import com.ssafy.ws.dto.UserInfo;
@Mapper
public interface UserMapper {
	UserInfo login(UserInfo userInfo);
	
	int regist(Attendance attendance);
	
	List<UserInfo> selectAll();
	
	Attendance selectByAno(String ano);
}
