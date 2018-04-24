package com.witkey.service;

import com.witkey.po.User;

public interface UserService {
	public User findUserById(Integer id);

	public Integer checkUserByEmail(String email);

	// 通过email和pwd检查用户
	public Integer checkUserByEmailAndPwd(User user);

	public Integer updateUser(User user);

	public Integer addUser(User user);

	// 通过用户id获取用户头像
	public String findUserHeadById(Integer id);

	// 重置密码
	public Integer resetPwd(String email, String pwd);

}
