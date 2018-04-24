package com.witkey.mapper;

import org.apache.ibatis.annotations.Param;

import com.witkey.po.User;

public interface UserMapper {
	// 通过Id查询用户
	public User findUserById(Integer id);

	// 通过email查询用户返回Id
	public Integer checkUserByEmail(String email);

	// 通过email和pwd检查用户
	public Integer checkUserByEmailAndPwd(User user);

	// 通过Id更新用户
	public Integer updateUser(User user);

	// 添加用户
	public Integer addUser(User user);

	// 通过用户id获取用户头像
	public String findUserHeadById(Integer id);

	// 重置密码
	public Integer resetPwd(@Param("email") String email, @Param("pwd") String pwd);

}
