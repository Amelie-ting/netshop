package com.netshop.dao;

import java.sql.SQLException;

import com.netshop.model.Admin;

public interface AdminLoginDao {
	
	/**
	 * ���ڹ���Ա��½
	 * @param name
	 * @param pas
	 * @return
	 * @throws SQLException 
	 */
	public Admin find(String name, String pas) throws SQLException ;
	

}
