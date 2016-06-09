package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.Admin;

public interface AdminLoginDao {
	
	/**
	 * 用于管理员登陆
	 * @param name
	 * @param pas
	 * @return
	 * @throws SQLException 
	 */
	public Admin find(String name, String pas) throws SQLException ;
	

}
