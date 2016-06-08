package com.netshop.service.implement;

import java.sql.SQLException;

import com.netshop.dao.UserDao;
import com.netshop.dao.implement.UserDaoImpl;
import com.netshop.exception.UserException;
import com.netshop.model.User;
import com.netshop.service.UserService;


/**
 * @ClassName: UserServiceImpl
 * @Description:用户模块业务层
 * @author hdm
 * @date 创建时间：2016年3月31日 上午9:28:01 @version=1.0
 */
public class UserServiceImpl implements UserService {
	private UserDao userDAO = new UserDaoImpl();

	/**
	 * 登录功能
	 * @param user
	 * @return
	 */
	public User login(User user) {
		try {
			return userDAO.findByLoginnameAndnpass(user.getU_name(), user.getU_password());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 注册功能
	 * 
	 * @param user
	 * @return
	 */
	public void regist(User user) {

		try {
			userDAO.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改密码
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws UserException
	 */
	public void updatePassword(int uid, String newPass, String oldPass) throws UserException {
		try {
			// 校验老密码
			boolean bool = userDAO.findByUidAndPassword(uid, oldPass);
			if (!bool) {// 如果老密码错误
				throw new UserException("老密码错误！");
			}
			// 修改密码
			userDAO.updatePassword(uid, newPass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 用户名注册校验
	 * @param uname
	 * @return
	 */
	public boolean ajaxValidateLoginname(String uname) {
		try {
			return userDAO.ajaxValidateLoginname(uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
