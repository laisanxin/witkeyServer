package com.witkey.mapper;

import org.apache.ibatis.annotations.Param;

import com.witkey.po.User;

public interface UserMapper {
	// ͨ��Id��ѯ�û�
	public User findUserById(Integer id);

	// ͨ��email��ѯ�û�����Id
	public Integer checkUserByEmail(String email);

	// ͨ��email��pwd����û�
	public Integer checkUserByEmailAndPwd(User user);

	// ͨ��Id�����û�
	public Integer updateUser(User user);

	// ����û�
	public Integer addUser(User user);

	// ͨ���û�id��ȡ�û�ͷ��
	public String findUserHeadById(Integer id);

	// ��������
	public Integer resetPwd(@Param("email") String email, @Param("pwd") String pwd);

}
