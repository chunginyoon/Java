package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.dto.Attendance;
import com.ssafy.ws.dto.UserInfo;
import com.ssafy.ws.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public UserInfo login(UserInfo userInfo) {
		return userMapper.login(userInfo);
	}

	@Override
	public int regist(Attendance attendance) {
		return userMapper.regist(attendance);
	}

	@Override
	public List<UserInfo> selectAll() {
		return userMapper.selectAll();
	}

	@Override
	public Attendance selectByAno(String ano) {
		return userMapper.selectByAno(ano);
	}
}