package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.Admin;
import com.netshop.model.User;

public interface AdLoginDao {
	
	//���û�������������û�
	public Admin find(String uname,String upassword) throws SQLException;

}
