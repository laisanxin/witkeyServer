package com.witkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.witkey.mapper.UserMapper;
import com.witkey.po.User;
import com.witkey.service.UserService;

@Service
//@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findUserById(Integer id) {
		
		return this.userMapper.findUserById(id);
	}


	@Override
	public Integer checkUserByEmailAndPwd(User user) {
		
		return this.userMapper.checkUserByEmailAndPwd(user);
	}

	@Override
	public Integer checkUserByEmail(String email) {
		
		return this.userMapper.checkUserByEmail(email);
	}


	@Override
	public Integer updateUser(User user) {
		
		return this.userMapper.updateUser(user) ;
	}


	@Override
	public Integer addUser(User user) {
		
		return this.userMapper.addUser(user);
	}


	@Override
	public String findUserHeadById(Integer id) {
		
		return this.userMapper.findUserHeadById(id);
	}


	@Override
	public Integer resetPwd(String email, String pwd) {
		
		return this.userMapper.resetPwd(email, pwd);
	}

}
