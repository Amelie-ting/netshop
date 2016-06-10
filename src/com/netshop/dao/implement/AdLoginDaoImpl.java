package com.netshop.dao.implement;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.netshop.dao.AdLoginDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.Admin;


public class AdLoginDaoImpl implements AdLoginDao {
	
	
	private QueryRunner qr = new DAO();
	
	@Override
	public Admin find(String uname, String upassword) throws SQLException {
		
		String sql = "select  * from admin where a_name=? and a_password=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), uname, upassword);
		
	}

}
