package com.netshop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
* @ClassName: JDBCTools
* @Description: 使用本类的方法，必须提供c3p0-copnfig.xml文件
* @author   hdm
* @date 创建时间：2016年4月1日 下午9:30:30
* @version=1.0
*/
public class JDBCTools {
	
	private static DataSource ds = null;

	//数据库连接池应只被初始化一次. 
	static{
		ds = new ComboPooledDataSource("c3p0");
	}
	
	/**
	 * 它为null表示没有事务
	 * 它不为null表示有事务
	 * 当开启事务时，需要给它赋值
	 * 当结束事务时，需要给它赋值为null
	 * 并且在开启事务时，让dao的多个方法共享这个Connection
	 */
private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * dao使用本方法来获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		Connection con = tl.get();
		if(con != null) return con;
		return ds.getConnection();
	}
	
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException {
		Connection con = tl.get();//获取当前线程的事务连接
		if(con != null) throw new SQLException("已经开启了事务，不能重复开启");
		con = ds.getConnection();//给con赋值，表示开启了事务
		con.setAutoCommit(false);//设置为手动提交
		tl.set(con);//把当前事务连接放到tl中
	}
	
	/**
	 * 提交事务
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();//获取当前线程的事务连接
		if(con == null) throw new SQLException("没有事务不能提交！");
		con.commit();//提交事务
		con.close();
		con = null;//表示事务结束！
		tl.remove();
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if(con == null) throw new SQLException("没有事务不能回滚！");
		con.rollback();
		con.close();
		con = null;
		tl.remove();
	}
	
	/**
	 * 释放Connection
	 * @param con
	 * @throws SQLException 
	 */
	public static void releaseDB(Connection connection) throws SQLException {
		Connection con = tl.get();
		if(connection != con) {//如果参数连接，与当前事务连接不同，说明这个连接不是当前事务，可以关闭
			if(connection != null &&!connection.isClosed()) {//如果参数连接没有关闭，关闭它
				connection.close();
			}
		}
	}
}
