package com.netshop.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/** 
* @ClassName: DAO
* @Description: 数据库增删改查
*/
public class DAO extends QueryRunner{
	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection con = JDBCTools.getConnection();
		int[] result = super.batch(con, sql, params);
		JDBCTools.releaseDB(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection con = JDBCTools.getConnection();
		T result = super.query(con, sql, rsh, params);
		JDBCTools.releaseDB(con);
		return result;
	}
	
	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JDBCTools.getConnection();
		T result = super.query(con, sql, rsh);
		JDBCTools.releaseDB(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con = JDBCTools.getConnection();
		int result = super.update(con, sql);
		JDBCTools.releaseDB(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = JDBCTools.getConnection();
		int result = super.update(con, sql, param);
		JDBCTools.releaseDB(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = JDBCTools.getConnection();
		int result = super.update(con, sql, params);
		JDBCTools.releaseDB(con);
		return result;
	}
	
}
