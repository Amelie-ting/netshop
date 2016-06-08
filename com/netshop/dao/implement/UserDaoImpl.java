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
* @Description: �û�ģ��־ò�
* @author   hdm
* @date ����ʱ�䣺2016��3��31�� ����8:11:43
* @version=1.0
*/
public class UserDaoImpl  implements UserDao{
	private QueryRunner qr = new DAO();
  	 /**
	 * ����û�
	 * @param user
	 */
	@Override
	public void add(User user) throws SQLException {
		String sql="insert into user(u_name,u_password,u_nickname) values(?,?,?)";
		Object[] params = {user.getU_name(),user.getU_password(),user.getU_nickname()};
		qr.update(sql, params);
	}

	/**
	 * �޸�����
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
	 * ��uid��password��ѯ
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
	 * ���û�������������û�
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
	 * У���û����Ƿ�ע��
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
