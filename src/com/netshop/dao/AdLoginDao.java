package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.Admin;
import com.netshop.model.User;

public interface AdLoginDao {
	
	//按用户名和密码查找用户
	public Admin find(String uname,String upassword) throws SQLException;

}
