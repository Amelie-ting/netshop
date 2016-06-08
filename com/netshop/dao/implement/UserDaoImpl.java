package com.netshop.dao.implement;

import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.netshop.dao.UserDao;
import com.netshop.jdbc.DAO;
import com.netshop.model.User;

/** 
* @ClassName: UserDaoImpl
* @Description: 用户模块持久层
* @author   hdm
* @date 创建时间：2016年3月31日 上午8:11:43
* @version=1.0
*/
public class UserDaoImpl  implements UserDao{
	private QueryRunner qr = new DAO();
  	 /**
	 * 添加用户
	 * @param user
	 */
	@Override
	public void add(User user) throws SQLException {
		String sql="insert into user(u_name,u_password,u_nickname) values(?,?,?)";
		Object[] params = {user.getU_name(),user.getU_password(),user.getU_nickname()};
		qr.update(sql, params);
	}

	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @throws SQLException
	 */
	@Override
	public void updatePassword(int uid, String password) throws SQLException {
		String sql = "update user set u_password=? where u_id=?";
		qr.update(sql, password, uid);
	}
	
	/**
	 * 按uid和password查询
	 * @param uid
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public boolean findByUidAndPassword(int uid, String password) throws SQLException {
		String sql = "select count(*) from user where u_id=? and u_password=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), uid, password);
		return number.intValue() > 0;
	}
	
	/**
	 * 按用户名和密码查找用户
	 * @param uid
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public User findByLoginnameAndnpass(String uname, String upassword) throws SQLException {
		String sql = "select  * from user where u_name=? and u_password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), uname, upassword);
	}
	
	/**
	 * 校验用户名是否注册
	 * @param loginname
	 * @return
	 * @throws SQLException 
	 */
	public boolean ajaxValidateLoginname(String loginname) throws SQLException {
		String sql = "select count(1) from user where u_name=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), loginname);
		return number.intValue() == 0;
	}
}
