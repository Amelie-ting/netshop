package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.User;

public interface UserDao {
	
	//添加用户信息
	public void add(User user) throws SQLException;
	
	//修改密码
	public void updatePassword(int uid, String password) throws SQLException;
	
	//按用户名和密码查找用户
	public User findByLoginnameAndnpass(String uname,String upassword) throws SQLException;
	
	//校验用户名是否注册
	public boolean ajaxValidateLoginname(String loginname) throws SQLException;

	//按uid和password查询
	public boolean findByUidAndPassword(int uid, String password) throws SQLException;

	
}
