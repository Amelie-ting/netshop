package com.netshop.dao.implement;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.netshop.dao.AdminLoginDao;
import com.netshop.model.Admin;

import cn.itcast.jdbc.TxQueryRunner;

public class AdminLoginDaoImpl implements AdminLoginDao {

	private QueryRunner qr = new TxQueryRunner();
	@Override
	public Admin find(String name, String pas) throws SQLException {
		String sql = "select * from t_admin where adminname=? and adminpwd=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), name, pas);
	}

}
