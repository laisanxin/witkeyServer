package com.witkey.service;

import com.witkey.po.User;

public interface UserService {
	public User findUserById(Integer id);

	public Integer checkUserByEmail(String email);

	// ͨ��email��pwd����û�
	public Integer checkUserByEmailAndPwd(User user);

	public Integer updateUser(User user);

	public Integer addUser(User user);

	// ͨ���û�id��ȡ�û�ͷ��
	public String findUserHeadById(Integer id);

	// ��������
	public Integer resetPwd(String email, String pwd);

}
