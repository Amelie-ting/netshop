package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.User;

public interface UserDao {
	
	//����û���Ϣ
	public void add(User user) throws SQLException;
	
	//�޸�����
	public void updatePassword(int uid, String password) throws SQLException;
	
	//���û�������������û�
	public User findByLoginnameAndnpass(String uname,String upassword) throws SQLException;
	
	//У���û����Ƿ�ע��
	public boolean ajaxValidateLoginname(String loginname) throws SQLException;

	//��uid��password��ѯ
	public boolean findByUidAndPassword(int uid, String password) throws SQLException;

	
}
